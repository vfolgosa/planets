FROM openjdk
MAINTAINER Vinicius Folgosa
ADD target/planets-0.0.1-SNAPSHOT.jar planets-service.jar
CMD ["java", "-jar" ,"./planets-service.jar"]
EXPOSE 8081