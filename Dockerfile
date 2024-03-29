#
# Build stage
#
FROM maven:3.8.3-openjdk-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -D skipTests

#
# Package stage
#
FROM openjdk:11
COPY --from=build /home/app/target/ecommerce-0.0.1-SNAPSHOT.jar /usr/local/lib/ecommerce-0.0.1-SNAPSHOT.jar
EXPOSE 8443
ENTRYPOINT ["java","-jar","/usr/local/lib/ecommerce-0.0.1-SNAPSHOT.jar"]