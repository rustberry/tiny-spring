package rust.tinyspring.context.support;

import rust.tinyspring.beans.factory.support.DefaultFactory;
import rust.tinyspring.beans.factory.support.xml.XmlBeanDefinitionReader;
import rust.tinyspring.context.ApplicationContext;
import rust.tinyspring.core.io.Resource;

public abstract class AbstractApplicationContext implements ApplicationContext {
    private DefaultFactory factory = null;

    public AbstractApplicationContext(String configFileName) {
        factory = new DefaultFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = getResource(configFileName);
        reader.loadBeanDefinition(resource);
    }

    public Object getBean(String beanID) {
        return factory.getBean(beanID);
    }

    protected abstract Resource getResource(String configFileName);
}
