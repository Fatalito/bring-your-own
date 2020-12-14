FROM adoptopenjdk/openjdk11:alpine-slim
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ARG JAR_FILE=dashboard/build/libs/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080