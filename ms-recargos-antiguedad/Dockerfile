FROM openjdk:21
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-recargos-antiguedad.jar
ENTRYPOINT ["java","-jar","/ms-recargos-antiguedad.jar"]