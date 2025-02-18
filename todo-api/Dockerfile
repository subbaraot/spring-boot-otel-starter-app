FROM openjdk:23-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw package

FROM openjdk:23-jdk-slim
WORKDIR /app
COPY --from=build /app/target/todo-api-1.0.0.jar .
ENTRYPOINT [ "java", "-jar", "todo-api-1.0.0.jar" ]