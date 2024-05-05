FROM ubuntu:latest AS build

RUN apt-get update 
RUN apt-get install openjdk-17-jdk -y

WORKDIR /app

COPY ./codigo/roteiro01/roteiro01 /app

RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /app/target/codigo/roteiro01/roteiro01-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]