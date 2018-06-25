package worker.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import worker.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class OcrService {

    private ITesseract iTesseract;

    public OcrService() {
        iTesseract = new Tesseract();
        Path path = Paths.get("src/main/resources/tessdata");
        iTesseract.setDatapath(path.toString());
        iTesseract.setLanguage("por");
    }

    public String process(String imageUrl) throws IOException, TesseractException {
        File temporaryFile = FileUtils.createTemporaryFile(imageUrl);
        String result = iTesseract.doOCR(temporaryFile);
        temporaryFile.delete();
        return result;
    }
}
