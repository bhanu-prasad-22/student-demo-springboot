# Step 1: Use official Java 21 runtime as base image
FROM openjdk:21-jdk-slim

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the built jar file into the container
# Make sure to replace the jar name if yours is different
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]