package rust.tinyspring.beans;

//import lombok.Getter;
//import lombok.Setter;
import rust.tinyspring.beans.factory.MutablePropertyValues;

public interface BeanDefinition {

    String getBeanName();
    void setBeanName(String beanName);

    String getBeanClassName();
    void setBeanClassName(String beanClassName);

    Class getBeanClass();

    MutablePropertyValues getPropertyValues();
}
