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
ENV APP_HOME /app
EXPOSE 8090
COPY /var/lib/jenkins/workspace/planets-api/target/$APP_FILE $APP_HOME/
WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]