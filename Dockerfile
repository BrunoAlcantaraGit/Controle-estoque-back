#FROM openjdk:22
#ADD ./docker-spring-boot.jar docker-spring-boot.jar
#ENTRYPOINT ["java","-jar","docker-spring-boot.jar"]

# Etapa de Build
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa final
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/docker-spring-boot.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]