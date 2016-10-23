FROM java:8-alpine
MAINTAINER Saatanan Koodarit Ry

ADD target/server.jar /urho/app.jar

EXPOSE 8090
ENV ENVIRONMENT=dev

CMD ["java", "-jar", "/urho/app.jar"]