# Usar la imagen base de OpenJDK
FROM openjdk:17-slim

# Instalar netcat
RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo jar construido
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que se ejecuta la aplicación
EXPOSE 8080

# Comando para esperar a MySQL antes de ejecutar la aplicación
CMD while ! nc -z db 3306; do sleep 1; done; java -jar app.jar
