# Etapa de construcción
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app
# Copiar el pom.xml y los fuentes
COPY pom.xml .
COPY src ./src
# Construir el artefacto
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM openjdk:17-slim
WORKDIR /app
# Copiar el artefacto de la etapa de construcción
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
# Instalar netcat
RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*
EXPOSE 8080
# Comando para esperar a MySQL antes de ejecutar la aplicación
CMD while ! nc -z db 3306; do sleep 1; done; java -jar app.jar
