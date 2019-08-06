package rust.tinyspring;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertNotNull;

public class GetResourceTest {
    @Test
    public void getResource() throws IOException {
        String fileName = "petStore-v1.xml";
        InputStream is = null;
//        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        ClassLoader cl = this.getClass().getClassLoader();
        is = cl.getResourceAsStream(fileName);
        assertNotNull(is);

        StringBuilder text = new StringBuilder();
        byte[] buf = new byte[256];
        int len = 0;

        while((len = is.read(buf)) != -1) {
            text.append(new String(buf, 0, len));
        }
        System.out.println(text);
    }
}
