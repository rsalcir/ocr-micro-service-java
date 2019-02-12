package ocr.resource;

import net.sourceforge.tess4j.TesseractException;
import ocr.service.OcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.async.DeferredResult;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@Path("ocr")
@Produces(MediaType.APPLICATION_JSON)
public class OcrApi {

    private static final int DELAY_MILLISECONDS = 10;
    private final Timer timer = new Timer();

    private OcrService ocrService;

    @Autowired
    public OcrApi(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    @GET
    public String status() {
        return "I'm working";
    }

    @POST
    public DeferredResult<String> process(LinkDTO dto) {
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
