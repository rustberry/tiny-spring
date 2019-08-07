package rust.tinyspring.core.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.assertNotNull;

@Slf4j
public class ResourceTest {
    String fileName;
    InputStream is;

    @After
    public void tearDown() throws Exception {
        StringBuilder text = new StringBuilder();
        byte[] buf = new byte[256];
        int len = 0;

        while((len = is.read(buf)) != -1) {
            text.append(new String(buf, 0, len));
        }
        log.info(String.valueOf(text));
    }

    @Before
    public void setUp() throws Exception {
        fileName = "petStore-v1.xml";
    }

    @Test
    public void testClassPathResource() throws IOException {
        Resource resource = new ClassPathResource(fileName);
        InputStream is = resource.getInputStream();
        assertNotNull(is);
        this.is = is;
    }

    @Test
    public void testFileSystemResource() throws IOException, URISyntaxException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL path = cl.getResource(fileName);
        String filePath = new File(path.toURI()).getPath();
        log.info("Absolute path: " + filePath);

        Resource resource = new FileSystemResource(filePath);
        InputStream is = resource.getInputStream();
        assertNotNull(is);
        this.is = is;
    }
}
