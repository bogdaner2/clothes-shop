FROM openjdk:8-jdk-alpine
RUN apk add curl
EXPOSE 5000:5000
CMD  while [[ "$(curl -s -o /dev/null -w ''%{http_code}'' config-server:8888/ping)" != "200" ]]; do echo "trying to connect to config-server" && sleep 5; done \
&& java -jar /usr/src/clothes-shop-api/target/clothes-shop-api-1.0-SNAPSHOT.jar