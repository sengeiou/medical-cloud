FROM java:8
VOLUME /tmp
ARG JAR_FILE
[p]
RUN bash -c 'touch /app.jar'
EXPOSE 9003
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]