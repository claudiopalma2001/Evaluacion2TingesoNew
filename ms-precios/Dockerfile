FROM openjdk:21
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-precios.jar
ENTRYPOINT ["java","-jar","/ms-precios.jar"]