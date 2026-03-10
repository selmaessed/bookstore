# Build-vaihe
FROM eclipse-temurin:17-jdk-focal AS builder
WORKDIR /opt/app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline

# Kopioi koko projekti, mukaan lukien application.properties
COPY . .

# Buildaa JAR
RUN ./mvnw clean install -DskipTests
RUN cp target/*.jar /opt/app/app.jar

# Runtime-vaihe
FROM eclipse-temurin:17-jre-alpine
WORKDIR /opt/app
COPY --from=builder /opt/app/app.jar /opt/app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]