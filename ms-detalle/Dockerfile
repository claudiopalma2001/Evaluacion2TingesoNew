FROM openjdk:21
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-detalle.jar
ENTRYPOINT ["java","-jar","/ms-detalle.jar"]