FROM openjdk:12-jre-slim

WORKDIR /app

COPY target/*.jar /app/jmfood.jar

EXPOSE 8080

CMD ["java", "-jar", "jmfood.jar"] 