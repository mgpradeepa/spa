#FROM openjdk:8-jdk-alpine
#RUN addgroup -S spa && adduser -S spa -G spa
#USER spa:spa
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]




FROM openjdk:17-alpine3.14 as build
WORKDIR /workspace/app

COPY spamod/mvnw .
COPY spamod/.mvn .mvn
COPY pom.xml .
COPY spamod/src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:17-alpine3.14
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","SpamodApplication"]