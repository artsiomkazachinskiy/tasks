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

# Prometheus Application Helm Chart

This repository contains a Helm chart for deploying the Prometheus application along with Terraform configuration for automated deployment.

## Prerequisites

- Kubernetes cluster
- kubectl configured
- Helm 3.x
- Terraform 1.x

## Directory Structure

```
.
├── Chart.yaml          # Helm chart metadata
├── values.yaml         # Default configuration values
├── templates/          # Kubernetes manifest templates
│   ├── deployment.yaml
│   └── service.yaml
├── main.tf            # Terraform configuration
└── README.md
```

## Installation

### Using Helm directly

```bash
# Install the chart
helm install prometheus-app ./

# Upgrade the chart
helm upgrade prometheus-app ./

# Uninstall the chart
helm uninstall prometheus-app
```

### Using Terraform

```bash
# Initialize Terraform
terraform init

# Plan the deployment
terraform plan

# Apply the configuration
terraform apply

# Destroy the deployment
terraform destroy
```

## Configuration

The following table lists the configurable parameters of the chart and their default values.

| Parameter | Description | Default |
|-----------|-------------|---------|
| `replicaCount` | Number of replicas | `1` |
| `image.repository` | Image repository | `prometheus-app` |
| `image.tag` | Image tag | `latest` |
| `image.pullPolicy` | Image pull policy | `IfNotPresent` |
| `service.type` | Kubernetes service type | `ClusterIP` |
| `service.port` | Service port | `8080` |
| `resources.limits.cpu` | CPU limit | `100m` |
| `resources.limits.memory` | Memory limit | `128Mi` |
| `resources.requests.cpu` | CPU request | `50m` |
| `resources.requests.memory` | Memory request | `64Mi` |

## Customizing Values

You can customize the values by either:

1. Modifying the `values.yaml` file
2. Using the `--set` flag with helm install/upgrade
3. Modifying the `set` blocks in `main.tf` for Terraform deployment