# Estágio de compilação (Build)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Estágio de execução (Run)
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/ecogrow-0.0.1-SNAPSHOT.jar ecogrow.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ecogrow.jar"]
