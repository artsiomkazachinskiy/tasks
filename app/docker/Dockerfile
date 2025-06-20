# Use an official Go runtime as a parent image
FROM golang:1.22-alpine AS builder

# Set the working directory in the container (test 1.3) 
WORKDIR /app

# Copy go mod and sum files
COPY go.mod go.sum ./

# Download dependencies
RUN go mod download

# Copy the local package source code
# Copy only the src directory which contains main.go and index.html
COPY src/ ./src/

# Build the Go app
# -o /app/main specifies the output file path and name
# ./src/main.go specifies the entrypoint
RUN go build -o /app/main ./src/main.go

# Use a smaller, non-developer image for the final stage
FROM alpine:latest

# Set the working directory for the final image
WORKDIR /app

# Copy the built executable from the builder stage
COPY --from=builder /app/main .
# Copy the index.html from the builder stage's src directory
COPY --from=builder /app/src/index.html .

# Expose port 8080 to the outside world
EXPOSE 8080

# Command to run the executable
CMD ["/app/main"]
