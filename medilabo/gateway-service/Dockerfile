FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace

COPY . .

RUN mvn -q -pl gateway-service -am clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /workspace/gateway-service/target/gateway-service-0.1.0-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
