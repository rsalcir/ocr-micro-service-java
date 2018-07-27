package resource;

import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import service.OcrService;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@EnableAutoConfiguration
@RequestMapping("/ocr")
public class OcrApi {

    private static final int DELAY_MILLISECONDS = 10;
    private final Timer timer = new Timer();

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
    public DeferredResult<String> process(@RequestBody LinkDTO dto) {
        final DeferredResult<String> deferredResult = new DeferredResult<>();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (deferredResult.isSetOrExpired()) {
                    throw new RuntimeException();
                } else {
                    try {
                        String processedText = ocrService.process(dto.url);
                        deferredResult.setResult(processedText);
                    } catch (IOException iOException) {
                        iOException.printStackTrace();
                    } catch (TesseractException tesseractException) {
                        tesseractException.printStackTrace();
                    }
                }
            }
        }, DELAY_MILLISECONDS);
        return deferredResult;
    }
}
