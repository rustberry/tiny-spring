package rust.tinyspring.context.test.v1;

import org.junit.Test;
import rust.tinyspring.beans.service.v1.PetStoreService;
import rust.tinyspring.context.ApplicationContext;
import rust.tinyspring.context.support.ClassPathXmlApplicationContext;
import rust.tinyspring.context.support.FileSystemXmlApplicationContext;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.assertNotNull;

public class ApplicationContextTest {
    String configFileName = "petStore-v1.xml";

    @Test
    public void testFileSystemXmlCtxGetBean() throws URISyntaxException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL path = cl.getResource(configFileName);
        String filePath = new File(path.toURI()).getPath();
        ApplicationContext ctx = new FileSystemXmlApplicationContext(filePath);
        PetStoreService petStoreService = (PetStoreService) ctx.getBean("petStore");
        assertNotNull(petStoreService);
    }

    @Test
    public void testClassPathXmlCtxGetBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configFileName);
        PetStoreService petService = (PetStoreService) ctx.getBean("petStore");
        assertNotNull(petService);
    }
}
