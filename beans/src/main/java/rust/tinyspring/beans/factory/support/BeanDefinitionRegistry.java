package rust.tinyspring.beans.factory.support;

import rust.tinyspring.beans.BeanDefinition;

public interface BeanDefinitionRegistry {

    BeanDefinition getBeanDefinition(String beanID);

    void registerBeanDefinition(String id, BeanDefinition beanDefinition);
}
