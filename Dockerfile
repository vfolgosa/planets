FROM openjdk:8-alpine
FROM maven:alpine

MAINTAINER Vinicius Folgosa

WORKDIR /code

ADD pom.xml /code/pom.xml
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

# Adding source, compile and package into a fat jar
ADD src /code/src
RUN mvn package -DskipTests=true

EXPOSE 8080

COPY target/*.jar app.jar

CMD ["java", "-jar", "-Dspring.profiles.active=prod" ,"./app.jar"]