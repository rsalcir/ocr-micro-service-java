#ocr-micro-service-java

#Construir container
``` sh
docker build -f Dockerfile -t ocr-micro-service-java .
```
#Executar container
``` sh
docker run -p 8085:8085 ocr-micro-service-java
```
