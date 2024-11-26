FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/franquicias-api-v2-0.0.2.jar
COPY ${JAR_FILE} franquicias_api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "franquicias_api.jar"]