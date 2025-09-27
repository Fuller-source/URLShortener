# Stage 1: Build the application
# **CORRECTION:** We use the official Maven image, which includes Java and Maven, 
# resolving the 'mvn: not found' error.
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven build file (pom.xml) and download dependencies
# The 'mvn' command is now available here
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire source code
COPY src ./src

# Build the application
RUN mvn clean install -DskipTests

# --- Final Stage: Runtime ---
# Use a smaller JRE image for the final runtime for security and size
FROM eclipse-temurin:21-jre-alpine

# Set the working directory for the final stage
WORKDIR /app

# Copy the packaged JAR file from the build stage
# Note: You need to confirm the exact JAR file name. If it's a Spring Boot fat JAR, 
# this path should be correct.
COPY --from=build /app/target/fuller-url-shortener-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port Spring Boot runs on (default is 8080)
EXPOSE 8080

# Define the command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "/app/app.jar"]