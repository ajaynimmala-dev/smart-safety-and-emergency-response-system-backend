FROM maven:3.9.11-eclipse-temurin-22

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests -B

EXPOSE 8082

ENTRYPOINT ["sh", "-c", "java -jar target/*.jar"]