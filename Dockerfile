FROM gradle:8.11.1-jdk23 AS build

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY src ./src

RUN gradle build --no-daemon -x test

FROM openjdk:23-jdk-slim

WORKDIR /app

RUN apt-get update && apt-get install -y curl

COPY --from=build /app/build/libs/*.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]
