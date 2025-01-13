#FROM openjdk:22
#ADD ./docker-spring-boot.jar docker-spring-boot.jar
#ENTRYPOINT ["java","-jar","docker-spring-boot.jar"]
# Etapa de Build
FROM ubuntu:22.04 AS build
WORKDIR /app

# Instalar dependências e JDK 22
RUN apt-get update && \
    apt-get install -y wget maven && \
    wget https://download.java.net/java/early_access/jdk22/1/GPL/openjdk-22-ea+1_linux-x64_bin.tar.gz && \
    tar -xzf openjdk-22-ea+1_linux-x64_bin.tar.gz -C /usr/local && \
    mv /usr/local/jdk-22* /usr/local/jdk-22

# Configurar o JAVA_HOME
ENV JAVA_HOME=/usr/local/jdk-22
ENV PATH=$JAVA_HOME/bin:$PATH

# Copiar e construir o projeto
COPY . .
RUN mvn clean package -DskipTests

# Etapa final
FROM ubuntu:22.04
WORKDIR /app

# Copiar JDK e aplicação do build
COPY --from=build /usr/local/jdk-22 /usr/local/jdk-22
ENV JAVA_HOME=/usr/local/jdk-22
ENV PATH=$JAVA_HOME/bin:$PATH
COPY --from=build /app/target/docker-spring-boot.jar app.jar

# Expor a porta
EXPOSE 8080

# Executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
