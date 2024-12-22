#!/bin/bash

# Función para verificar si un comando existe
command_exists() {
    type "$1" &> /dev/null
}

# Detiene todos los contenedores en ejecución y elimina los contenedores, las redes, los volúmenes y las imágenes creadas
if command_exists docker-compose; then
    docker-compose down
    docker-compose up --build -d
elif command_exists docker; then
    docker compose down
    docker compose up --build -d
else
    echo "docker-compose no está instalado, y docker no está disponible o no soporta 'compose'."
    exit 1
fi
