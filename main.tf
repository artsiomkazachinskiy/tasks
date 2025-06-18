terraform {
  required_providers {
    helm = {
      source  = "hashicorp/helm"
      version = "~> 2.12"
    }
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "~> 2.24"
    }
  }
}

provider "helm" {
  kubernetes {
    config_path = "~/.kube/config"
  }
}

resource "helm_release" "prometheus_app" {
  name       = "prometheus-app"
  chart      = "./"
  namespace  = "default"
  
  set {
    name  = "replicaCount"
    value = "1"
  }

  set {
    name  = "image.repository"
    value = "prometheus-app"
  }

  set {
    name  = "image.tag"
    value = "latest"
  }

  set {
    name  = "service.type"
    value = "ClusterIP"
  }

  set {
    name  = "service.port"
    value = "8080"
  }

  # Ingress configuration
  set {
    name  = "ingress.enabled"
    value = "true"
  }

  set {
    name  = "ingress.hosts[0].host"
    value = "prometheus-app.local"
  }

  # ConfigMap configuration
  set {
    name  = "configMap.enabled"
    value = "true"
  }

  set {
    name  = "configMap.data.LOG_LEVEL"
    value = "info"
  }

  # Secrets configuration
  set {
    name  = "secrets.enabled"
    value = "true"
  }

  set {
    name  = "secrets.data.API_KEY"
    value = base64encode("your-api-key-here")
  }

  # Health checks configuration
  set {
    name  = "healthChecks.livenessProbe.enabled"
    value = "true"
  }

  set {
    name  = "healthChecks.readinessProbe.enabled"
    value = "true"
  }

  # Prometheus configuration
  set {
    name  = "prometheus.enabled"
    value = "true"
  }

  set {
    name  = "prometheus.annotations.prometheus\\.io/scrape"
    value = "true"
  }
} 