FROM openjdk:8


RUN apt-get update -y && apt-get install maven -y

MAINTAINER Vinicius Folgosa
ENV dir /tmp

VOLUME ${dir}
ADD . ${dir}
WORKDIR ${dir} 
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]
RUN mvn package -DskipTests=true


EXPOSE 8081
ADD target/planets-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "-Dspring.profiles.active=prod" ,"./app.jar"]