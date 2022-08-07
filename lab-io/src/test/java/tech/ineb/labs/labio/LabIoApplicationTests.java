package tech.ineb.labs.labio;

import org.apache.logging.log4j.core.util.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

public class LabIoApplicationTests {

    @Test
    public void testByteStream() {
    }

    @Test
    public void testCharacterStream(){

    }

    static <T> T mapobjectfromjsonfile(String jsonPath, Class<T> clazz) throws IOException {
        ClassLoader classLoader = LabIoApplicationTests.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(jsonPath)).getFile());
        String data = readFileToString(file, Charset.forName("UTF-8"));
        T obj = objectMapper.readValue(data, clazz);
        return obj;
    }

    static String readFileToString(File file, Charset encoding) throws IOException {
        InputStream in = null;
        try {
            in = openInputStream(file);
            return IOUtils.toString(in, Charsets.toCharset(encoding));
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
}
