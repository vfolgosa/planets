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

FROM openjdk:8-jre-alpine
ENV APP_FILE planets-0.0.1-SNAPSHOT.jar
VOLUME /tmp
COPY . /tmp
WORKDIR /tmp

RUN apt-get install maven -y

RUN mvn clean install

EXPOSE 8090

ADD target/$APP_FILE app.jar


CMD ["exec java -jar app.jar"]