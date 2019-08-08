package rust.tinyspring.context.support;

import rust.tinyspring.beans.factory.support.DefaultBeanFactory;
import rust.tinyspring.core.io.ClassPathResource;
import rust.tinyspring.core.io.Resource;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private DefaultBeanFactory factory;

    public ClassPathXmlApplicationContext(String configFileName) {
        super(configFileName);
    }

    @Override
    protected Resource getResource(String configFileName) {
        return new ClassPathResource(configFileName);
    }
}
