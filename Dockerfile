FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN mkdir -p /prod/
ADD $HOME/openshift/conf/prod /prod
COPY $HOME/openshift/conf/prod/prod.env /prod/prod.env
EXPOSE 9096
ENTRYPOINT ["java","-jar","/app.jar"]
