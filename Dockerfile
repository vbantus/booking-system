FROM maven:slim AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn clean install -DskipTests

# For Java 11
FROM adoptopenjdk/openjdk11:alpine-jre
# cd /app
WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/booking-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# java -jar /app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
