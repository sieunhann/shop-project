FROM openjdk:17.0.2-oraclelinux8

COPY target/project-0.0.1-SNAPSHOT.jar project-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/project-0.0.1-SNAPSHOT.jar"]