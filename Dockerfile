FROM openjdk:latest

WORKDIR /app

COPY ./build/libs/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]