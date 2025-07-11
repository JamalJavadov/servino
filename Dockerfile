#--------------------------------------------------
# Stage 1: Build the application with Maven
#--------------------------------------------------
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# 1. Copy only the pom.xml to download dependencies
# This layer is only invalidated when the pom.xml changes.
COPY pom.xml .
RUN mvn dependency:go-offline

# 2. Copy the rest of the source code
# This layer is invalidated when source code changes.
COPY src ./src

# 3. Build the application. 'package' is sufficient.
# Tests are run by default. Use -DskipTests if you test elsewhere.
RUN mvn -X package -DskipTests


#--------------------------------------------------
# Stage 2: Create the final, lightweight runtime image
#--------------------------------------------------
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# 4. Copy only the application JAR from the builder stage
COPY --from=builder /app/target/business-project-0.0.1-SNAPSHOT.jar .

# 5. Set the command to run the application
CMD ["java", "-jar", "business-project-0.0.1-SNAPSHOT.jar"]