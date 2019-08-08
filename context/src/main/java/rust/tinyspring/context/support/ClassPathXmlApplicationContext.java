package rust.tinyspring.context.support;

import rust.tinyspring.beans.factory.support.DefaultFactory;
import rust.tinyspring.beans.factory.support.xml.XmlBeanDefinitionReader;
import rust.tinyspring.context.ApplicationContext;
import rust.tinyspring.core.io.ClassPathResource;
import rust.tinyspring.core.io.Resource;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private DefaultFactory factory;

    public ClassPathXmlApplicationContext(String configFileName) {
        super(configFileName);
    }

    @Override
    protected Resource getResource(String configFileName) {
        return new ClassPathResource(configFileName);
    }
}
