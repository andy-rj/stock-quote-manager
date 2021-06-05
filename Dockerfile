####
# This Dockerfile is used in order to build a container that runs in JVM mode
#
# Before building the container image run:
#
# ./mvnw package
#
###

FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
