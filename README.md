# spring-boot-otel-starter-app

A Spring Boot API application with instrumentation and Open Telemetry integration.

## Build

```bash
cd todo-api
docker build -t todo-api:1.0 .
```

## Run

The simplest mode to run the app with basic observability is using [Grafana LGTM](https://grafana.com/blog/2024/03/13/an-opentelemetry-backend-in-a-docker-image-introducing-grafana/otel-lgtm/). Run the following command to start the application, MongoDB and LGTM containers.

```bash
docker-compose -f compose-lgtm.yaml up
```

To run the version with the observability stack as discrete containers, run the following command

```bash
docker-compose -f compose-full.yaml up
```

Do the following when running the full version the first time

1. Open http://localhost:3000 in a browser to access Grafana.
2. Login using the default Grafana credentials.
    - User name: admin
    - Password: admin
3. Change the password of the `admin` user.
4. After logging into Grafana, navigate to `Connections`->`Data sources`.
    - Click on `Add data source` and select Prometheus.
    - Use `http://prometheus:9090` as the URL for the Prometheus server.
    - Click on `Save & test` to save the Prometheus data source.
    - Go back to `Data sources` and select `Add new data source` and select Loki
    - Use `http://loki:3100` as the URL.
    - Click on `Save & test` to save the Loki data source.
    - Go back to `Data sources` and select `Add new data source` and select Tempo
    - Use `http://tempo:3200` as the URL.
    - Click on `Save & test` to save the Tempo data source.
5. To link Traces to Logs, follow the steps below
    - In the Tempo data source view, scroll to the *Trace to logs* section.
    - Select `Loki` as the Data source.
    - Select `Use custom query`
    - Enter `` {service_name=~".+"} | trace_id =`${__span.traceId}` `` in the Query field.
    - Click on `Save & test` to save the Tempo data source.

> The data from Grafana and Tempo containers will be stored in the `data` folder and all the configuration changes made in the Grafana will be persisted until the data folder is removed.

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
