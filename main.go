package main

import (
	"fmt"
	"net/http"
	"os"

	"github.com/prometheus/client_golang/prometheus"
	"github.com/prometheus/client_golang/prometheus/promhttp"
)

var pageVisits = prometheus.NewCounter(
	prometheus.CounterOpts{
		Name: "page_visits_total",
		Help: "Total number of visits to the main page.",
	},
)

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
	// Create a new Prometheus registry.
	reg := prometheus.NewRegistry()
	// Register the counter.
	reg.MustRegister(pageVisits)

	// Add the promhttp handler to the Mux.
	// Note: Using http.DefaultServeMux here. For custom mux, you'd pass it to ListenAndServe.
	http.Handle("/metrics", promhttp.HandlerFor(reg, promhttp.HandlerOpts{}))
	http.HandleFunc("/", handler)

	fmt.Println("Server starting on port 8080...")
	fmt.Println("Metrics available at /metrics")
	err := http.ListenAndServe(":8080", nil) // Using DefaultServeMux
	if err != nil {
		fmt.Println("Error starting server:", err)
	}
}
