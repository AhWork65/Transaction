FROM openjdk:8
EXPOSE 8091
ADD target/transaction.jar transaction.jar
ENTRYPOINT ["java", "-jar", "/transaction.jar"]



