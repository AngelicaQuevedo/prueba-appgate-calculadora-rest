FROM openjdk:11 as build
copy target/calculadora-rest-0.0.1-SNAPSHOT.jar calculadora-rest-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "/calculadora-rest-0.0.1-SNAPSHOT.jar"]
