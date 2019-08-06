package rust.tinyspring.factory.support;

import lombok.Getter;
import lombok.Setter;
import rust.tinyspring.BeanDefinition;
import rust.tinyspring.exception.BeansException;
import rust.tinyspring.factory.MutablePropertyValues;

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
