FROM eclipse-temurin:25-jdk-ubi10-minimal AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw package

FROM eclipse-temurin:25-jre-ubi10-minimal
WORKDIR /app
COPY --from=build /app/target/todo-api-1.0.0.jar .
ENTRYPOINT [ "java", "-jar", "todo-api-1.0.0.jar" ]
