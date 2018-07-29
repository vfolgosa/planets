#install OS
FROM centos
#install java
RUN yum install -y java
#make directory structure to store temporary files
RUN mkdir -p /store
#put jar into container
ADD target/planets-0.0.1-SNAPSHOT.jar planets-service.jar
EXPOSE 8090
#run jar
ENTRYPOINT ["java", "-jar", "/planets-service.jar"]