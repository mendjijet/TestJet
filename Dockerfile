FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ADD /openshift/conf/prod/prod.env /openshift/conf/prod/prod.env
EXPOSE 9096
ENTRYPOINT ["java","-jar","/app.jar", "--spring.config.location=classpath:file:/openshift/conf/prod/prod.env"]
