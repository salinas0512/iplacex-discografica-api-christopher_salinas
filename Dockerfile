# Stage 1: Build con Gradle
FROM gradle:8.5-jdk21 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle build --no-daemon

# Stage 2: Ejecutar con OpenJDK
FROM openjdk:21-jdk-slim
COPY --from=build /app/build/libs/discografica-1.jar /app/app.jar
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
