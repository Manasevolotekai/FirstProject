# FirstProject - Integrate Centralized Logging using Opentelemery and Grafana

This project is a Spring Boot REST API for managing products. It also includes centralized logging:

- **OpenTelemetry Collector**
- **Grafana**
- **Loki**
- **PostgreSQL**
- **Docker Compose**

---

## Features

- Full CRUD API for `Product`
- Observability with OpenTelemetry
- Centralized logging with Loki and Grafana
- Containerized with Docker Compose
- JSON logging with Logback  

---

## Tech Stack

- Java 17
- Spring Boot
- PostgreSQL
- Docker & Docker Compose
- OpenTelemetry
- Grafana + Loki

---


## Run with Docker Compose

Make sure you have **Docker** and **Docker Compose** installed.Run a test API request.Then View logs in the terminal:

Then run:

```bash
  docker-compose up --build
  
curl http://localhost:8080/api/products

docker-compose logs -f app


