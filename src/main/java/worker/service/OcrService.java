package worker.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.springframework.stereotype.Service;
import worker.utils.FileUtils;

import java.io.File;
import java.io.IOException;

@Service
public class OcrService {

    private ITesseract iTesseract;

    public OcrService() {
        iTesseract = new Tesseract();
        iTesseract.setDatapath(LoadLibs.extractTessResources("tessdata").getAbsolutePath());
        iTesseract.setLanguage("por");
    }

    public String process(String imageUrl) throws IOException, TesseractException {
        File temporaryFile = FileUtils.createTemporaryFile(imageUrl);
        String result = iTesseract.doOCR(temporaryFile);
        temporaryFile.delete();
        return result;
    }
}
