# git-tasks

## Building and Running Locally

To build and run the application:

1.  Build the Go application:
    ```bash
    go build
    ```

2.  Run the executable. The executable name is `prometheus-app` (derived from the `go.mod` file: `module example.com/prometheus-app`).
    ```bash
    ./prometheus-app
    ```

The application will be available at `http://localhost:8080`.
Prometheus metrics will be available at `http://localhost:8080/metrics`.

## Running with Docker Compose

To run the application and Prometheus using Docker Compose:

1.  Build and start the services in detached mode:
    ```bash
    docker-compose up -d --build
    ```

2.  Access the application in your browser at: `http://localhost:8080`

3.  Access Prometheus in your browser at: `http://localhost:9090`

4.  To check the `page_visits_total` metric in Prometheus:
    *   Navigate to `http://localhost:9090`.
    *   In the expression bar, type `page_visits_total`.
    *   Click the "Execute" button. You should see the current count of visits to the main application page.