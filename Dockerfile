FROM amazoncorretto:11-alpine-jdk

ARG JAR_FILE

COPY ${JAR_FILE} app.jar
COPY application-db.yml /application-db.yml

ENTRYPOINT ["/bin/sh","-c", \
    "java -jar -Dspring.config.location=classpath:/application.properties,/home/ec2-user/app/application-oauth.properties, \
    /home/ec2-user/app/application-aws.properties,/home/ec2-user/app/application-real-db.properties \
    -Dspring.profiles.active=real ./app.jar"]