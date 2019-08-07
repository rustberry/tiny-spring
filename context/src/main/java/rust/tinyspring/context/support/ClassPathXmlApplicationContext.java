package rust.tinyspring.context.support;

import rust.tinyspring.beans.factory.support.DefaultFactory;
import rust.tinyspring.beans.factory.support.xml.XmlBeanDefinitionReader;
import rust.tinyspring.context.ApplicationContext;

public class ClassPathXmlApplicationContext implements ApplicationContext {

    private DefaultFactory factory;

    public ClassPathXmlApplicationContext(String configFileName) {
        factory = new DefaultFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(configFileName);
    }

    @Override
    public Object getBean(String name) {
        return factory.getBean(name);
    }
}
