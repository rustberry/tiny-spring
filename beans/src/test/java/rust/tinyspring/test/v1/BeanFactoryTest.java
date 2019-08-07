package rust.tinyspring.test.v1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rust.tinyspring.BeanDefinition;
import rust.tinyspring.exception.BeansException;
import rust.tinyspring.factory.support.DefaultFactory;
import rust.tinyspring.factory.support.xml.XmlBeanDefinitionReader;
import rust.tinyspring.service.v1.PetStoreService;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class BeanFactoryTest {
    String configFileName;
    DefaultFactory factory;
    XmlBeanDefinitionReader reader;

    // This will be invoked before every @Test
    @Before
    public void setUp() throws Exception {
        configFileName = "petStore-v1.xml";
        factory = new DefaultFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }

    @Test
    public void invalidBean() {
        reader.loadBeanDefinition(configFileName);
        // This test aims at testing on actions that are deliberately designed to fail.
        try {
            factory.getBean("invalidBeanName");
        } catch (BeansException e) {
            return;
        }
        // If execution arrives here, it means that no exception was thrown, failed.
        Assert.fail("Invalid bean name was not detected");
    }

    @Test
    public void invalidXML() {
        try {
            reader.loadBeanDefinition("invalidXml.xml");
        } catch (Exception e) {
            return;
        }
        Assert.fail("Invalid XML was not detected");
    }

    @Test
    public void getBean() {
        reader.loadBeanDefinition(configFileName);

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
