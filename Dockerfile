FROM openjdk:11
EXPOSE 8090
ADD build/libs/ABET-Back-End-0.0.1-SNAPSHOT.jar Back.jar
ENTRYPOINT ["java","-jar","Back.jar"]