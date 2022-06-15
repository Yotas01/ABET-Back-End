FROM openjdk:11
EXPOSE 80
ADD build/libs/ABET-Back-End-0.0.1-SNAPSHOT.jar Back.jar
ENTRYPOINT ["java","-jar","Back.jar"]