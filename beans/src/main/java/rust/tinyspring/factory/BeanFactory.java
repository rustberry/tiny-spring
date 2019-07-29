package rust.tinyspring.factory;

import rust.tinyspring.BeanDefinition;

public interface BeanFactory {
    Object getBean(String name);

    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
