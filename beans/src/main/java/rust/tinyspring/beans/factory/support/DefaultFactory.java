package rust.tinyspring.beans.factory.support;

import java.util.HashMap;
import java.util.Map;

import rust.tinyspring.beans.BeanDefinition;
import rust.tinyspring.beans.exception.BeansException;
import rust.tinyspring.beans.factory.BeanFactory;

public class DefaultFactory extends AbstractBeanFactory implements BeanDefinitionRegistry {

    /**
     * 存放 bean 的定义，不仅存在 Map 中，BeanDefinition 的子类 GenericBeanDefinition 中也要存储，方便扩展
     */
    private final Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * 初始化类定义
     */
    public DefaultFactory() {
        ;
    }

/*
    @Override
    public Object getBean(String beanName) {
        // TODO
        return null;
    }
*/

    @Override
    public BeanDefinition getBeanDefinition(String id) {
        return beanDefinitionMap.get(id);
    }

    @Override
    public void registerBeanDefinition(String id, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(id, beanDefinition);
    }


}
