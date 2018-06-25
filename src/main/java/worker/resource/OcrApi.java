package worker.resource;

import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import worker.service.OcrService;

import java.io.IOException;

@RestController
@RequestMapping("/ocr")
public class OcrApi {

    private OcrService ocrService;

    @Autowired
    public OcrApi(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    @GetMapping
    public String status() {
        return "I'm working";
    }

    @PostMapping
    public String start(@RequestBody String url) throws IOException, TesseractException {
        return ocrService.process(url);
    }
}
