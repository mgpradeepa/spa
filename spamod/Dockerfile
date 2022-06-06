## The below one works in local after the build. As it sees the jar file ready.

#FROM openjdk:8-jdk-alpine
#RUN addgroup -S spa && adduser -S spa -G spa
#USER spa:spa
##ARG JAR_FILE=target/*.jar
#COPY target/*.jar  app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

## When the file is not there we are trying to build and then publish

FROM openjdk:16-alpine3.13

WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod -R 755 ./mvnw

RUN ./mvnw dependency:go-offline
COPY src ./src
