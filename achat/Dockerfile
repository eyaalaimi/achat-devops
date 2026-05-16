# Dockerfile for Achat Spring Boot Application
# Stage 1: Build the application using Maven
FROM maven:3.8.4-openjdk-11-slim AS build

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:11-jre-slim

# Add metadata
LABEL maintainer="achat-devops@esprit.tn"
LABEL description="Application de gestion des achats"
LABEL version="1.0.0"

# Create app user for security (non-root)
RUN useradd -m -s /bin/bash appuser

WORKDIR /app

# Copy the JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Change ownership to appuser
RUN chown appuser:appuser app.jar

# Switch to non-root user
USER appuser

# Expose the application port
EXPOSE 8082

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8082/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
