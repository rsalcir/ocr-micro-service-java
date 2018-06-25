package worker.utils;

import java.io.*;
import java.net.URL;

public class FileUtils {

    public static File createTemporaryFile(String link) throws IOException {
        File temporaryFile = new File("tempImage.jpg");
        URL url = new URL(link);
        InputStream inputStream = url.openStream();
        OutputStream outputStream = new FileOutputStream(temporaryFile);
        byte[] bytes = new byte[2048];
        int length;
        while ((length = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, length);
        }
        inputStream.close();
        outputStream.close();
        return temporaryFile;
    }
}
