FROM centos

RUN yum install -y java

ADD target/planets-service.jar planets-service.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "/planets-service.jar"]