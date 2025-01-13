#FROM openjdk:22
#ADD ./docker-spring-boot.jar docker-spring-boot.jar
#ENTRYPOINT ["java","-jar","docker-spring-boot.jar"]
# Etapa de Build
# Etapa de Build
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar os arquivos do projeto
COPY . .

# Construir o projeto (sem testes para agilizar o processo)
RUN mvn clean package -DskipTests

# Etapa Final
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiar o JAR do build
COPY --from=build /app/target/*.jar app.jar

# Expor a porta usada pelo Spring Boot
EXPOSE 3000

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
