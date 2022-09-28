FROM maven:3.8.2-adoptopenjdk-11 AS build
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -B verify -f /usr/src/app/pom.xml clean package

FROM openjdk:11-jre-slim
COPY --from=build /usr/src/app/target/exemplo-0.0.1-SNAPSHOT.jar /usr/app/exemplo-0.0.1-SNAPSHOT.jar  
EXPOSE 8080  
ENTRYPOINT ["java","-jar","/usr/app/exemplo-0.0.1-SNAPSHOT.jar"]