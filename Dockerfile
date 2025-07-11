FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN ./mvnw clean install

CMD ["java", "-jar", "target/business-project-0.0.1-SNAPSHOT.jar"]
