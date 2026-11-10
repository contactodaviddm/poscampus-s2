
# Etapa 1: Construcción del proyecto
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar configuración Maven
COPY pom.xml .

# Copiar código fuente
COPY src ./src

# Compilar proyecto
RUN mvn clean package -DskipTests

# Etapa 2: Ejecutar aplicación
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiar archivo JAR generado
COPY --from=build /app/target/*.jar app.jar

# Puerto de Render
ENV PORT=10000

EXPOSE 10000

# Ejecutar Spring Boot
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
