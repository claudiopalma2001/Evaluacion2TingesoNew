FROM openjdk:21
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-descuentos-cantidad-reparaciones.jar
ENTRYPOINT ["java","-jar","/ms-descuentos-cantidad-reparaciones.jar"]