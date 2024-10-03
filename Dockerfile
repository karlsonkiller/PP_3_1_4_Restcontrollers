FROM openjdk:24-ea-17-slim-bullseye
FROM mysql:8.0.33
WORKDIR /app
COPY out/artifacts/spring_boot_security_demo_jar/spring-boot_security-demo.jar /app/restcontroller.jar

ENTRYPOINT ["java", "-jar", "restcontroller.jar"]