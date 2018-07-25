FROM openjdk:8-alpine
FROM maven:alpine

ENV dir /tmp

VOLUME ${dir}

MAINTAINER Vinicius Folgosa

ADD . ${dir}

WORKDIR ${dir} 

RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]
RUN mvn package -DskipTests=true

EXPOSE 8080

ADD target/planets-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "-Dspring.profiles.active=prod" ,"./app.jar"]