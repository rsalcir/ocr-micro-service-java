package service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class OcrService {

    private static final String TESSERACT_DATA = "tessdata";
    private ITesseract iTesseract;

    public OcrService() {
        iTesseract = new Tesseract();
        iTesseract.setDatapath(LoadLibs.extractTessResources(TESSERACT_DATA).getAbsolutePath());
    }

    public String process(String imageUrl) throws IOException, TesseractException {
        URL url = new URL(imageUrl);
        InputStream inputStream = url.openStream();
        BufferedImage imageBuffered = ImageIO.read(inputStream);
        String processedText = iTesseract.doOCR(imageBuffered);
        return processedText;
    }
}
