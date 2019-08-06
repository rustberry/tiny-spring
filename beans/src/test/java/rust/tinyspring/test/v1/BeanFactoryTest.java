package rust.tinyspring.test.v1;

import org.junit.Test;
import rust.tinyspring.BeanDefinition;
import rust.tinyspring.factory.support.DefaultFactory;
import rust.tinyspring.service.v1.PetStoreService;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class BeanFactoryTest {

    @Test
    public void getBean() {

        String configFileName = "petStore-v1.xml";
        DefaultFactory factory = new DefaultFactory(configFileName);
        BeanDefinition bd = factory.getBeanDefinition("petStore");

        assertEquals("rust.tinyspring.service.v1.PetStoreService", bd.getBeanClassName());

        PetStoreService petStoreService = (PetStoreService) factory.getBean("petStore");

        assertNotNull(petStoreService);

    }

    @Test
    public void getResource() throws IOException {
        String fileName = "petStore-v1.xml";
        InputStream is = null;
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
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
