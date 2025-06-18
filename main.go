package main

import (
	"fmt"
	"net/http"
	"os"
	"time"

	"github.com/prometheus/client_golang/prometheus"
	"github.com/prometheus/client_golang/prometheus/promhttp"
)

var (
	// Counter for total page visits
	pageVisits = prometheus.NewCounter(
		prometheus.CounterOpts{
			Name: "page_visits_total",
			Help: "Total number of visits to the main page.",
		},
	)

	// Histogram for request duration
	requestDuration = prometheus.NewHistogramVec(
		prometheus.HistogramOpts{
			Name:    "http_request_duration_seconds",
			Help:    "Duration of HTTP requests in seconds",
			Buckets: prometheus.DefBuckets,
		},
		[]string{"method", "endpoint"},
	)

	// Counter for HTTP status codes
	httpStatusCodes = prometheus.NewCounterVec(
		prometheus.CounterOpts{
			Name: "http_status_codes_total",
			Help: "Total number of HTTP status codes",
		},
		[]string{"status_code"},
	)

	// Gauge for active requests
	activeRequests = prometheus.NewGauge(
		prometheus.GaugeOpts{
			Name: "http_active_requests",
			Help: "Number of active HTTP requests",
		},
	)
)

func init() {
	// Register all metrics
	prometheus.MustRegister(pageVisits)
	prometheus.MustRegister(requestDuration)
	prometheus.MustRegister(httpStatusCodes)
	prometheus.MustRegister(activeRequests)
}

// Middleware для отслеживания метрик
func metricsMiddleware(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		start := time.Now()
		activeRequests.Inc()
		defer activeRequests.Dec()

		// Создаем ResponseWriter для перехвата статус кода
		rw := &responseWriter{
			ResponseWriter: w,
			statusCode:     http.StatusOK,
		}

		next.ServeHTTP(rw, r)

		// Записываем метрики
		duration := time.Since(start).Seconds()
		requestDuration.WithLabelValues(r.Method, r.URL.Path).Observe(duration)
		httpStatusCodes.WithLabelValues(fmt.Sprintf("%d", rw.statusCode)).Inc()
	})
}

// responseWriter для перехвата статус кода
type responseWriter struct {
	http.ResponseWriter
	statusCode int
}

func (rw *responseWriter) WriteHeader(code int) {
	rw.statusCode = code
	rw.ResponseWriter.WriteHeader(code)
}

func handler(w http.ResponseWriter, r *http.Request) {
	pageVisits.Inc()
	htmlContent, err := os.ReadFile("index.html")
	if err != nil {
		http.Error(w, "Could not read index.html", http.StatusInternalServerError)
		fmt.Println("Error reading index.html:", err)
		return
	}
	w.Header().Set("Content-Type", "text/html")
	w.Write(htmlContent)
}

func main() {
	// Create a new Prometheus registry
	reg := prometheus.NewRegistry()

	// Register all metrics
	reg.MustRegister(pageVisits)
	reg.MustRegister(requestDuration)
	reg.MustRegister(httpStatusCodes)
	reg.MustRegister(activeRequests)

	// Add the promhttp handler to the Mux
	http.Handle("/metrics", promhttp.HandlerFor(reg, promhttp.HandlerOpts{}))

	// Wrap the main handler with metrics middleware
	http.HandleFunc("/", metricsMiddleware(http.HandlerFunc(handler)).ServeHTTP)

	fmt.Println("Server starting on port 8080...")
	fmt.Println("Metrics available at /metrics")
	err := http.ListenAndServe(":8080", nil)
	if err != nil {
		fmt.Println("Error starting server:", err)
	}
}
