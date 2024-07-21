FROM openjdk:22-jdk-slim

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src

RUN apt-get update \
    && apt-get install -y maven \
    && rm -rf /var/lib/apt/lists/*

RUN mvn -f /app/pom.xml clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "/app/target/bank-0.0.1-SNAPSHOT.jar"]
