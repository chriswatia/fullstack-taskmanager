# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the compiled Spring Boot JAR file into the container
COPY target/*.jar app.jar

# Expose the port that the Spring Boot app will listen on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
