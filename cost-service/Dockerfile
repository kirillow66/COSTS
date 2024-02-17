FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} cost_app.jar
ENTRYPOINT ["java", "-jar", "cost_app.jar"]