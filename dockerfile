FROM openjdk:11-jre-slim

WORKDIR /app

ARG JAR_FILE

COPY target/*.jar /app/jmfood.jar

COPY wait-for-it.sh /wait-for-it.sh

EXPOSE 8080

CMD ["java", "-jar", "jmfood.jar"] 