# Stage 1: Build con Gradle
FROM gradle:8.5-jdk21 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle build -x test --no-daemon

# Stage 2: Ejecutar con OpenJDK
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
