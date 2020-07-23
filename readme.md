
#ocr-micro-ocr.service-java

Micro serviço em java com springboot utilizando tesseract ocr


#Construir container
``` sh
docker build -f Dockerfile -t ocr-micro-ocr.service-java .
```
#Executar container
``` sh
docker run -p 8085:8085 ocr-micro-ocr.service-java
```
