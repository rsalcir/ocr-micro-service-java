FROM ubuntu
MAINTAINER Alcir Jr <rsalcir@gmail.com>

RUN apt-get update && apt-get install -y \
  software-properties-common \
  git \
  gcc

RUN \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install -y oracle-java8-installer && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /var/cache/oracle-jdk8-installer

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle  
  
RUN add-apt-repository ppa:alex-p/tesseract-ocr && apt-get update

RUN apt-get install -y \
  libleptonica-dev \
  libtesseract4 \
  libtesseract-dev \
  tesseract-ocr

RUN apt-get install -y \
  tesseract-ocr-por \
  tesseract-ocr-eng

WORKDIR /
ADD ./target/worker-ocr.jar /worker-ocr.jar
EXPOSE 8085
CMD ["java", "-jar", "worker-ocr.jar"]
