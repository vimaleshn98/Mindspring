FROM maven:3.6-alpine as builder
COPY . /app 
WORKDIR /app
RUN mvn -DskipTests package

FROM openjdk:8-alpine
COPY --from=builder /app/target/employe-jdbc-0.0.1-SNAPSHOT.jar /app.jar
CMD java -jar /app.jar
