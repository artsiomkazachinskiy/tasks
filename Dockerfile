# Use an official Go runtime as a parent image
FROM golang:1.21-alpine AS builder

# Set the working directory in the container
WORKDIR /app

# Copy go mod and sum files
COPY go.mod go.sum ./

# Download dependencies
RUN go mod download

# Copy the local package source code
COPY . .

# Build the Go app
# CGO_ENABLED=0 disables Cgo
# GOOS=linux ensures the binary is built for Linux
# -a installs the packages dependencies
# -ldflags="-w -s" reduces the binary size by stripping debug information
RUN CGO_ENABLED=0 GOOS=linux go build -a -ldflags="-w -s" -o /app/main .

# Use a specific version of alpine for the final stage
FROM alpine:3.19

# Set the working directory for the final image
WORKDIR /app

# Create a non-root user and group
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copy the built executable and index.html from the builder stage, and set ownership
COPY --from=builder --chown=appuser:appgroup /app/main .
COPY --chown=appuser:appgroup index.html .

# Switch to non-root user
USER appuser

# Expose port 8080 to the outside world
EXPOSE 8080

# Command to run the executable
CMD ["/app/main"]
