FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

# ADD THIS LINE to give execute permission
RUN chmod +x mvnw

RUN ./mvnw clean install

CMD ["java", "-jar", "target/business-project-0.0.1-SNAPSHOT.jar"]
