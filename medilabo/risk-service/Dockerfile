FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace

COPY . .

RUN mvn -q -pl risk-service -am clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /workspace/risk-service/target/risk-service-0.1.0-SNAPSHOT.jar app.jar

EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
