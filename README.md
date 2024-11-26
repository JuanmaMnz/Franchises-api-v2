# FRANCHISES API

Java | Springboot | Mysql | Docker 



## Tabla de Contenido

- [Guía de instalación](#getting-started)
  - [Prerrequisitos](#prerrequisitos)
  - [Backend - Local](#back-end-local)
  - [Backend - Docker](#back-end-docker)   
- [Documentación de la API](#documentacion)
- [Errores y soluciones](#errores-soluciones)
  - [Docker](#docker-errores)


## 🛠️ Guía de instalación

A continuación, se encuentran las instrucciones para poner en marcha el proyecto.

### Prerrequisitos

Asegúrese de tener instalado lo siguiente en su entorno local


- Java 17
- Spring Boot 3.3.5
- Maven v4.0.0
- MySQL: Base de datos relacional utilizada
    - database: Asegurese de tener una DB con el nombre "franchises_db"
    - username: root
    - password: password
    - port: 3306
    - Conexión JDBC: Configurada en `application.properties` para establecer conexión con MySQL.
- Variables de entorno:
    - Encontradas en el archivo `.env` del proyecto y en  `application-local.properties`


### instalación

#### Backend: Local

- Clona el repositorio: 
`git clone https://github.com/JuanmaMnz/Franchises-api-v2`
- Abra el proyecto en su IDE.
- Abra un terminal de comandos dentro del IDE.

#### Backend: Local Setup

- En la terminal de comandos, ejecute el siguiente comando: 
`./mvnw spring-boot:run`

- El servidor se ejecutará en: http://localhost:8080

#### Backend: Docker Setup

- Asegúrate de terminar el proceso del backend en la consola (si se está ejecutando localmente) para evitar conflictos de puertos

- Inicia Docker o abre Docker Desktop

- En la terminal, ejecuta el siguiente comando

`./start-docker.sh`

- El backend se iniciará levantando el contenedor de Docker con el servidor.

- Una vez que el contenedor esté en funcionamiento ...

- El servidor se ejecutará en: http://localhost:8080

- Puedes detener el contenedor de Docker con el comando 
./stop-dev.sh

- Después de esto, ve a Docker Desktop y elimina containers, images, volumes, and builds para evitar errores y conflictos de caché antes de levantarlo nuevamente.

## 📖 Documentación de la API 

La documentación completa de la API se puede encontrar y probar en la interfaz de Swagger. Una vez que la aplicación esté en ejecución

puedes acceder a la documentación en: `http://localhost:8080/swagger-ui.html`

 [Documentación en Swagger](http://localhost:8080/swagger-ui.html "Documentación en Swagger")

## ⚠️ Errores y soluciones

### Docker

ERROR Wsl 2:

- Esto indica que la máquina que intenta ejecutar Docker no tiene un distribuidor de Linux instalado y/o la virtualización no está habilitada en su BIOS.
- Este problema debe resolverse para poder ejecutar Docker Desktop
  - src: [Docker Desktop WSL 2](https://docs.docker.com/desktop/wsl/)

ERROR GENÉRICO:

- Para cualquier otro error relacionado con la integración de Docker y el backend, se recomienda limpiar el caché de Docker ejecutando los siguientes comandos:

    - `docker container prune -f`
    - `docker image prune -a -f`
    - `docker network prune -f`
    - `docker volume prune -f`


