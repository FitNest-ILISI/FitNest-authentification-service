# Use the official OpenJDK image as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file into the container
COPY target/auth_service-0.0.1-SNAPSHOT.jar authentication-service.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "authentication-service.jar"]
