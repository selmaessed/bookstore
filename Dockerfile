FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Kopioi koko projekti
COPY . .

# Buildaa Spring Boot jar
RUN mvn clean package -DskipTests

# Runtime image
FROM eclipse-temurin:17-jre

WORKDIR /app

# Kopioi buildattu jar
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]