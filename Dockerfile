FROM amazoncorretto:11-alpine-jdk

ARG JAR_FILE

ARG MAIN_DIRECTORY=/usr/mydesk-api
WORKDIR $MAIN_DIRECTORY

COPY *.properties ./
COPY ${JAR_FILE} ./app.jar

ENTRYPOINT ["/bin/sh","-c", \
    "java -jar -Dspring.config.location=classpath:/application.properties,/usr/mydesk-api/application-oauth.properties,/usr/mydesk-api/application-aws.properties,/usr/mydesk-api/application-real-db.properties -Dspring.profiles.active=real ./app.jar"
]
