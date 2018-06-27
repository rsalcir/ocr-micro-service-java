package worker.resource;

import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import worker.service.OcrService;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    public DeferredResult<String> process(@RequestBody String url) {
        final DeferredResult<String> deferredResult = new DeferredResult<>();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (deferredResult.isSetOrExpired()) {
                    throw new RuntimeException();
                } else {
                    try {
                        String processedText = ocrService.process(url);
                        deferredResult.setResult(processedText);
                    } catch (IOException e) {
                        throw new RuntimeException(e.getMessage());
                    } catch (TesseractException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }
            }
        }, DELAY_MILLISECONDS);
        return deferredResult;
    }
}
