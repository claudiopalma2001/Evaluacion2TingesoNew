FROM openjdk:21
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-recargos-kilometraje.jar
ENTRYPOINT ["java","-jar","/ms-recargos-kilometraje.jar"]