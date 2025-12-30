FROM eclipse-temurin:25-jdk-alpine-3.23 AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw package

FROM eclipse-temurin:25-jre-alpine-3.23
WORKDIR /app
COPY --from=build /app/target/todo-api-1.0.0.jar .
ENTRYPOINT [ "java", "-jar", "todo-api-1.0.0.jar" ]
