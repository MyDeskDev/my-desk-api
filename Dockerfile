FROM ghcr.io/graalvm/jdk:ol8-java17-22.3.0

COPY ./build/libs/api.jar api.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=local", "-jar", "api.jar"]
