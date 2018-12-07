FROM openjdk:8u181-jdk-alpine

RUN	apk add --no-cache bash

COPY target/universal/docker_newline-*?.zip /tmp/docker_newline.zip

RUN mkdir -p "/opt/mn" \
    && unzip -qd "/opt/mn/" /tmp/docker_newline.zip \
    && rm -rf /tmp/docker_newline

EXPOSE 8080

ENTRYPOINT ["/opt/mn/docker_newline/bin/docker_newline"]