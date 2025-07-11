FROM maven:3.9.6-eclipse-temurin-21

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw -v

CMD ["java", "-jar", "target/business-project-0.0.1-SNAPSHOT.jar"]
