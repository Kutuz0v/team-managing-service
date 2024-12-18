FROM openjdk:17-jdk-slim AS build
LABEL authors="Mykhailo Kutuzov"

COPY gradle /team-managing-service/gradle
COPY gradlew /team-managing-service/

COPY . /team-managing-service/

WORKDIR /team-managing-service

RUN chmod +x gradlew

RUN ./gradlew build -x test

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /team-managing-service/build/libs/team-managing-service.jar /app/team-managing-service.jar

CMD ["java", "-jar", "team-managing-service.jar"]

EXPOSE 5000
