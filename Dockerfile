FROM openjdk:8-jre-alpine
ENV APP_FILE planets-0.0.1-SNAPSHOT.jar
ENV APP_HOME /app
EXPOSE 8090
COPY target/$APP_FILE $APP_HOME/
WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]
