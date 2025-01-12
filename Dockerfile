#FROM openjdk:22
#ADD ./docker-spring-boot.jar docker-spring-boot.jar
#ENTRYPOINT ["java","-jar","docker-spring-boot.jar"]

FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk:17 -y
COPY . .
RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:17

EXPOSE 3000

COPY --from=build /target/docker-spring-boot.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]