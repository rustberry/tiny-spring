package rust.tinyspring.factory.support;

import rust.tinyspring.BeanDefinition;

public interface BeanDefinitionRegistry {

    BeanDefinition getBeanDefinition(String beanID);

    void registerBeanDefinition(String id, BeanDefinition beanDefinition);
}
