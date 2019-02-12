
#ocr-micro-ocr.service-java

#Construir container
``` sh
docker build -f Dockerfile -t ocr-micro-ocr.service-java .
```
#Executar container
``` sh
docker run -p 8085:8085 ocr-micro-ocr.service-java
```
