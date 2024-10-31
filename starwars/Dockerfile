FROM openjdk:17-alpine AS builder

WORKDIR /app

COPY . .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=builder /app/target/starwars-0.0.1-SNAPSHOT.jar .

EXPOSE 8005

ENTRYPOINT ["java","-jar","starwars-0.0.1-SNAPSHOT.jar"]