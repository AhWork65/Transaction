FROM openjdk:8
EXPOSE 8092
ADD target/transaction.jar transaction.jar
ENTRYPOINT ["java", "-jar", "/transaction.jar"]



