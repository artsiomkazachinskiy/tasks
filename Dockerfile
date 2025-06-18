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
# -o /app/main specifies the output file path and name
# . specifies that the main package is in the current directory
RUN go build -o /app/main .

# Use a smaller, non-developer image for the final stage
FROM alpine:latest

# Set the working directory for the final image
WORKDIR /app

# Copy the built executable from the builder stage
COPY --from=builder /app/main .
COPY index.html .

# Expose port 8080 to the outside world
EXPOSE 8080

# Command to run the executable
CMD ["/app/main"]
