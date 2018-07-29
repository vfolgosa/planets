#FROM openjdk
#VOLUME /tmp
#COPY . /tmp
#WORKDIR /tmp
#MAINTAINER Vinicius Folgosa
#RUN pwd
#RUN ls
#ADD target/planets-0.0.1-SNAPSHOT.jar planets-service.jar
#CMD ["java", "-jar" ,"./planets-service.jar"]
#EXPOSE 8081
ENV dir /tmp
VOLUME ${dir}
FROM maven:3.5-jdk-8-alpine
WORKDIR ${dir}
COPY . ${dir}
RUN mvn install 


FROM openjdk:8-jre-alpine
ENV APP_FILE planets-0.0.1-SNAPSHOT.jar
WORKDIR ${dir}

EXPOSE 8090

ADD target/$APP_FILE app.jar


CMD ["exec java -jar app.jar"]