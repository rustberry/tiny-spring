package rust.tinyspring.beans.test.v1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rust.tinyspring.beans.BeanDefinition;
import rust.tinyspring.beans.exception.BeansException;
import rust.tinyspring.beans.factory.support.DefaultBeanFactory;
import rust.tinyspring.beans.factory.support.xml.XmlBeanDefinitionReader;
import rust.tinyspring.beans.service.v1.PetStoreService;
import rust.tinyspring.core.io.ClassPathResource;
import rust.tinyspring.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BeanFactoryTest {
    String configFileName;
    DefaultBeanFactory factory;
    XmlBeanDefinitionReader reader;
    Resource resource;

    // This will be invoked before every @Test
    @Before
    public void setUp() {
        configFileName = "petStore-v1.xml";
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        resource = new ClassPathResource(configFileName);
    }

    @Test
    public void invalidBean() {
        reader.loadBeanDefinition(resource);
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
            resource = new ClassPathResource("invalidXml.xml");
            reader.loadBeanDefinition(resource);
        } catch (Exception e) {
            return;
        }
        Assert.fail("Invalid XML was not detected");
    }

    @Test
    public void getBean() {
        reader.loadBeanDefinition(resource);

        BeanDefinition bd = factory.getBeanDefinition("petStore");

        assertEquals("rust.tinyspring.beans.service.v1.PetStoreService", bd.getBeanClassName());

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
