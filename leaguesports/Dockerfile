FROM maven:3.8.5-openjdk-11-slim as builder
WORKDIR /project
COPY pom.xml /project/
RUN mvn -B clean verify
COPY src /project/src
RUN mvn -B -o package

FROM openjdk:11-jdk-slim
WORKDIR /usr/src/app/
COPY --from=builder project/target/*.jar /usr/src/app/
EXPOSE 8443
ENTRYPOINT exec java -jar leaguesports-0.0.1-SNAPSHOT.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar pruebas.jar
