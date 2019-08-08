package rust.tinyspring.beans.factory.config;

import rust.tinyspring.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory  extends BeanFactory {
    void setBeanClassLoader(ClassLoader classLoader);
    ClassLoader getClassLoader();
}
