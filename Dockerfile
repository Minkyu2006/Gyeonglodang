FROM openjdk:8-jdk-alpine
RUN echo "Asia/Seoul" > /etc/timezone
VOLUME /tmp
ARG JAR_FILE
COPY ./target/homeservice-1.0.0.jar  app.jar

ENTRYPOINT ["java","-javaagent:/scouter.agent.jar","-Dscouter.config=/scouter.conf","-Djava.net.preferIPv4Stack=true -Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]