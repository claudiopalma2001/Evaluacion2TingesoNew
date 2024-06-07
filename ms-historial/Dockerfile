FROM openjdk:21
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-historial.jar
ENTRYPOINT ["java","-jar","/ms-historial.jar"]