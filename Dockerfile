FROM openjdk:8
ADD target/worker-ocr.jar worker-ocr.jar
EXPOSE 8085
ENTRYPOINT ["java", "- jar", "worker-ocr.jar"]