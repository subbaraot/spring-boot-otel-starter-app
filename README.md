# spring-boot-otel-starter-app

A Spring Boot API application with instrumentation and Open Telemetry integration.

## Build

```bash
cd todo-api
docker build -t todo-api:1.0 .
```

## Run

```bash
cd todo-api
docker-compose up
```

### Use the app

1. Go to http://localhost:8585 in a Web Browser to access the Swagger page of the API.
2. Use the `POST /items` endpoint to create a new TODO item.
3. Use the `GET /items` endpoint to see the all the TODO items created.
4. Use the `PUT /items/{id}?isDone=[true|false]` to mark a TODO item as 'Done' or 'Not Done'.

### Accessing Metrics, Logs and Traces

1. Go to http://localhost:3000 in a Web Browser to access Grafana.
2. Use the "Explore" option to view the Logs (Loki), Metrics (Prometheus) and Traces (Tempo).

## Built With

- JDK v23
- [Spring Boot 3.4.2](https://spring.io/projects/spring-boot)
- [Docker](https://www.docker.com/)
- [Grafana LGTM](https://grafana.com/blog/2024/03/13/an-opentelemetry-backend-in-a-docker-image-introducing-grafana/otel-lgtm/)
- [MongoDB](https://www.mongodb.com/)
- [Open Telemetry Spring Boot Starter](https://opentelemetry.io/docs/zero-code/java/spring-boot-starter/)

## Development Tools

- [VS Code](https://code.visualstudio.com/)
- [VS Code Spring Boot Extensions](https://code.visualstudio.com/docs/java/java-spring-boot)
