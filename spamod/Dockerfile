FROM openjdk:8-jdk-alpine
RUN addgroup -S spa && adduser -S spa -G spa
USER spa:spa
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]