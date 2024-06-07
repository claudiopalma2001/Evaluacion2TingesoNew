FROM openjdk:21
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-descuentos-bonos.jar
ENTRYPOINT ["java","-jar","/ms-descuentos-bonos.jar"]