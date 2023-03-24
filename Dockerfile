FROM openjdk:17
ADD /target/SpringForumApp-0.0.1-SNAPSHOT.jar springforumapp.jar
ENTRYPOINT ["java", "-jar", "springforumapp.jar"]