package worker.service;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import worker.utils.FileUtils;

import java.io.File;
import java.io.IOException;

@Service
public class OcrService {

    private final tesseract.TessBaseAPI api;

    public OcrService(@Value("${tessdata.dir:classpath:/tessdata}") File tesseractData,
                      @Value("${tessdata.lang:por}") String tesseractLang) {
        this.api = new tesseract.TessBaseAPI();
        String path = tesseractData.toPath().toString();
        if (this.api.Init(path, tesseractLang) != 0) {
            throw new IllegalStateException("Error initializing tesseract.");
        }
    }

    public String process(String imageUrl) throws IOException {
        File temporaryFile = FileUtils.createTemporaryFile(imageUrl);
        return readFile(temporaryFile);
    }

    public String readFile(File file) {
        BytePointer outText = null;
        lept.PIX image = null;
        try {
            image = lept.pixRead(file.getAbsolutePath());
            this.api.SetImage(image);
            outText = this.api.GetUTF8Text();
            file.delete();
            return outText == null ? "" : outText.getString();
        } finally {
            if (outText != null) {
                outText.deallocate();
            }
            if (image != null) {
                lept.pixDestroy(image);
            }
        }
    }
}
