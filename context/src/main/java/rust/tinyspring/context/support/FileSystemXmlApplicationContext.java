package rust.tinyspring.context.support;

import rust.tinyspring.beans.factory.BeanFactory;
import rust.tinyspring.beans.factory.support.DefaultFactory;
import rust.tinyspring.beans.factory.support.xml.XmlBeanDefinitionReader;
import rust.tinyspring.context.ApplicationContext;
import rust.tinyspring.core.io.FileSystemResource;
import rust.tinyspring.core.io.Resource;

public class FileSystemXmlApplicationContext extends AbstractApplicationContext {
    private DefaultFactory factory;

    public FileSystemXmlApplicationContext(String configFileName) {
        super(configFileName);
    }

    @Override
    protected Resource getResource(String configFileName) {
        return new FileSystemResource(configFileName);
    }
}
