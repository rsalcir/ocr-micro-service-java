package ocr.resource;

import ocr.base.ApiTest;
import ocr.service.OcrService;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class OcrApiTest extends ApiTest {

    private static final String URL = "ocr";
    private OcrService ocrService;

    @Override
    protected List<Object> ResourcesRegister() {
        ocrService = mock(OcrService.class);
        return Collections.singletonList(new OcrApi(ocrService));
    }

    @Test
    public void testStatusOcrApi() {
        String statusExpected = "I'm working";

        String status = target(URL).request().get(String.class);

        assertEquals(statusExpected, status);
    }
}
