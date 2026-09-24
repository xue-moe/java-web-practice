FROM maven:3.9-eclipse-temurin-8 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B package

FROM tomcat:9.0-jre8-temurin
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /workspace/target/hr-management.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
