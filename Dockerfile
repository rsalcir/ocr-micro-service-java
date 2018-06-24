FROM java:8
MAINTAINER Alcir Jr <rsalcir@gmail.com>

WORKDIR /
ADD ./target/worker-ocr.jar /worker-ocr.jar
EXPOSE 8085
CMD ["java", "-jar", "worker-ocr.jar"]
