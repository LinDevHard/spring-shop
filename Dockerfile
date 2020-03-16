FROM openjdk:11-oracle
MAINTAINER LinDevHard
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/shop-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar","/app.jar", "--spring.profiles.active=docker"]