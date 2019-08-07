package rust.tinyspring.beans.factory.support;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import rust.tinyspring.beans.BeanDefinition;
import rust.tinyspring.beans.exception.BeansException;
import rust.tinyspring.beans.factory.MutablePropertyValues;

@Slf4j
public class GenericBeanDefinition implements BeanDefinition {
    @Getter
    @Setter
    private String beanClassName;

    @Getter
    @Setter
    private String beanName;

    @Getter
    private Class beanClass;

    private MutablePropertyValues propertyValues;

    @Override
    public Class getBeanClass() {
        Class clz = null;
        try {
            clz = Class.forName(this.beanClassName);
        } catch (ClassNotFoundException e) {
            log.error("Class: " + this.beanClassName + " not found");
            throw new BeansException("Class: " + this.beanClassName + " not found");
        }
        return clz;
    }

    public MutablePropertyValues getPropertyValues() {
        return this.propertyValues;
    }

    public GenericBeanDefinition(String beanID, String BeanClassName) {
        this.beanName = beanID;
        this.beanClassName = BeanClassName;
    }
}
