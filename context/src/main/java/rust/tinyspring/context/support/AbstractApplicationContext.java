package rust.tinyspring.context.support;

import rust.tinyspring.base.util.ClassUtil;
import rust.tinyspring.beans.factory.support.DefaultBeanFactory;
import rust.tinyspring.beans.factory.support.xml.XmlBeanDefinitionReader;
import rust.tinyspring.context.ApplicationContext;
import rust.tinyspring.core.io.Resource;

public abstract class AbstractApplicationContext implements ApplicationContext {
    private DefaultBeanFactory factory = null;
    private ClassLoader beanClassLoader;

    public AbstractApplicationContext(String configFileName) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = getResource(configFileName);
        reader.loadBeanDefinition(resource);
    }

    @Override
    public Object getBean(String beanID) {
        return factory.getBean(beanID);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    @Override
    public ClassLoader getClassLoader() {
        return this.beanClassLoader != null ? this.beanClassLoader : ClassUtil.getClassLoader();
    }

    protected abstract Resource getResource(String configFileName);
}
