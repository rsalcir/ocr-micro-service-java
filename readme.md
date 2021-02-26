
# ocr-micro-service-java

Projeto de estudo para aplicar os conceitos de microserviços e docker feito em java com springboot utilizando tesseract ocr

# Construir container
``` sh
docker build -f Dockerfile -t ocr-micro-ocr.service-java .
```
# Executar container
``` sh
docker run -p 8085:8085 ocr-micro-ocr.service-java
```
