FROM openjdk:11.0.6-jre
EXPOSE 9096
ADD target/location-0.0.1-SNAPSHOT.jar  location-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar" , "location-0.0.1-SNAPSHOT.jar"]