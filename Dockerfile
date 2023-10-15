FROM ubuntu:latest AS build

RUN apt update && apt upgrade -y && apt install openjdk-17-jdk -y

COPY . .

RUN apt install maven -y && mvn clean install
RUN rm -rf /var/lib/apt/lists/* /var/cache/apt/archives/*

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /target/to-do-list-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]