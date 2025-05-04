# Use a Java 21 Alpine image
FROM eclipse-temurin:21-jdk-alpine

# ARG to get the built jar
ARG JAR_FILE=target/*.jar

# Copy the jar into container
COPY ${JAR_FILE} app.jar

# IMPORTANT: Start Java with debug agent enabled
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5007", "-jar", "/app.jar"]
