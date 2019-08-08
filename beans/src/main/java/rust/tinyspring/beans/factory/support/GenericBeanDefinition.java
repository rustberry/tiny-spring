package rust.tinyspring.beans.factory.support;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import rust.tinyspring.base.util.ClassUtil;
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
    public Class<?> getBeanClass() {
        Class<?> clz = this.beanClass;
        if (clz != null) return clz;
        return getBeanClass(ClassUtil.getClassLoader());
    }

    public Class<?> getBeanClass(ClassLoader classLoader) {
        Class<?> clz = null;
        try {
            clz = resolveBeanClass(classLoader);
        } catch (ClassNotFoundException e) {
            log.error("Class: " + this.beanClassName + " not found");
            throw new BeansException("Class: " + this.beanClassName + " not found");
        }
        return clz;

    }

    @Override
    public MutablePropertyValues getPropertyValues() {
        return this.propertyValues;
    }

    public GenericBeanDefinition(String beanID, String BeanClassName) {
        this.beanName = beanID;
        this.beanClassName = BeanClassName;
    }

    public Class<?> resolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException {
        String className = getBeanClassName();
        if (className == null) {
            return null;
        }
        Class<?> resolvedClass = Class.forName(this.beanClassName, true, classLoader);
        this.beanClass = resolvedClass;
        return resolvedClass;
    }
}
