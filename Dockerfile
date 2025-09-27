FROM openjdk:21-jdk-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven build file (pom.xml) and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire source code
COPY src ./src

# Build the application
# The "-DskipTests" flag is added here to prevent tests from running during the build phase
# on the server, which saves time.
RUN mvn clean install -DskipTests

# --- Final Stage: Runtime ---
# Use a smaller base image for the final runtime for security and size
FROM openjdk:21-jre-slim

# Copy the packaged JAR file from the build stage
COPY --from=build /app/target/fuller-url-shortener-0.0.1-SNAPSHOT.jar /app.jar

# Expose the port Spring Boot runs on (default is 8080)
EXPOSE 8080

# Define the command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "/app.jar"]