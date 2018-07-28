FROM openjdk:8


RUN apt-get update -y && apt-get install maven -y

MAINTAINER Vinicius Folgosa

ENV dir /tmp

VOLUME ${dir}

WORKDIR ${dir}

COPY . ${dir}
 
RUN mvn package -DskipTests=true
RUN ls target
ADD target/planets-0.0.1-SNAPSHOT.jar app.jar
RUN ls target

EXPOSE 8081
CMD ["java", "-jar", "-Dspring.profiles.active=prod" ,"./app.jar"]