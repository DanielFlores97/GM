# Usamos la imagen ligera de Temurin con Java 21
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiamos el JAR compilado 
COPY target/*.jar app.jar

# Exponemos el puerto donde corre Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
