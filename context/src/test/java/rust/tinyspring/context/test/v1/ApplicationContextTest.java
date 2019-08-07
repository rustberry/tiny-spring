package rust.tinyspring.context.test.v1;

import org.junit.Test;
import rust.tinyspring.beans.service.v1.PetStoreService;
import rust.tinyspring.context.ApplicationContext;
import rust.tinyspring.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

public class ApplicationContextTest {

    @Test
    public void testGetBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petStore-v1.xml");
        PetStoreService petService = (PetStoreService) ctx.getBean("petStore");
        assertNotNull(petService);
    }
}
