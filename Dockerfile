# Estágio de compilação (Build)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Estágio de execução (Run)
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/ecogrow-0.0.1-SNAPSHOT.jar ecogrow.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ecogrow.jar"]
