FROM openjdk:11
ADD /target/calculadora-rest-0.0.1-SNAPSHOT.jar calculadora-rest-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "calculadora-rest-0.0.1-SNAPSHOT.jar"]
