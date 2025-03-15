# Etapa 1: Construir o projeto com Maven e Java 21
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Cache de dependências para acelerar builds subsequentes
RUN mvn dependency:go-offline

# Construir o projeto
RUN mvn clean package -DskipTests

# Etapa 2: Criar a imagem final com o OpenJDK 21
FROM openjdk:21-jdk-slim

# Criar um diretório para a aplicação
RUN mkdir /app

# Copiar o JAR construído da etapa de build
COPY --from=build /app/target/*.jar /app/app.jar

# Definir o diretório de trabalho
WORKDIR /app

# Expor a porta da aplicação
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "app.jar"]