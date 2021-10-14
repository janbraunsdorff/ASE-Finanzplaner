FROM gradle:jdk16 as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle test --stacktrace
RUN gradle build --refresh-dependencies --stacktrace

FROM openjdk:16-slim

COPY --from=builder /home/gradle/src/build/libs/ASE-Finanzplaner-1.0-SNAPSHOT.jar ./app.jar
COPY ./repository ./repository

EXPOSE 8080
CMD ["java", "--enable-preview","-jar","/app.jar"]