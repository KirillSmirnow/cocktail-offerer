FROM openjdk:17
WORKDIR /opt
COPY *.jar .
ENTRYPOINT exec java -Xms256M -Xmx256M -Dspring.profiles.active=$PROFILE -jar *.jar
