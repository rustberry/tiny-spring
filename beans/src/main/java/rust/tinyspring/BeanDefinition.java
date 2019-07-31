package rust.tinyspring;

import lombok.Getter;
import lombok.Setter;
import rust.tinyspring.factory.MutablePropertyValues;

public class BeanDefinition {

    @Getter
    @Setter
    private String beanClassName;

    @Getter
    @Setter
    private String beanName;

    @Getter
    private Class beanClass;

    private MutablePropertyValues propertyValues;

    public MutablePropertyValues getPropertyValues() {
        return this.propertyValues;
    }
}
