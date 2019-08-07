package rust.tinyspring.factory.support;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import rust.tinyspring.BeanDefinition;
import rust.tinyspring.exception.BeansException;
import rust.tinyspring.factory.BeanFactory;

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
