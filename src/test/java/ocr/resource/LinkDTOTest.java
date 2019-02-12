package ocr.resource;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinkDTOTest {

    @Test
    public void testCreateLinkDTO() {
        String urlExpected = "http://www.myimage.com.br";

        LinkDTO linkDTO = new LinkDTO();
        linkDTO.url = urlExpected;

        assertEquals(urlExpected, linkDTO.url);
    }
}
