FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace

COPY . .

RUN mvn -q -pl front-service -am clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /workspace/front-service/target/front-service-0.1.0-SNAPSHOT.jar app.jar

EXPOSE 8084
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
