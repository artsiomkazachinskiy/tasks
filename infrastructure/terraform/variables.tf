variable "namespace" {
  description = "Kubernetes namespace for the application"
  type        = string
  default     = "prometheus-app"
}

variable "image_repository" {
  description = "Docker image repository"
  type        = string
  default     = "prometheus-app"
}

variable "image_tag" {
  description = "Docker image tag"
  type        = string
  default     = "latest"
}

variable "replica_count" {
  description = "Number of replicas"
  type        = number
  default     = 1
}

variable "service_port" {
  description = "Service port"
  type        = number
  default     = 8080
}

variable "ingress_enabled" {
  description = "Enable ingress"
  type        = bool
  default     = true
}

variable "ingress_host" {
  description = "Ingress host"
  type        = string
  default     = "prometheus-app.local"
} 