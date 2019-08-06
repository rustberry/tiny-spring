package rust.tinyspring;

//import lombok.Getter;
//import lombok.Setter;
import rust.tinyspring.factory.MutablePropertyValues;

public interface BeanDefinition {

    String getBeanName();
    void setBeanName(String beanName);

    String getBeanClassName();
    void setBeanClassName(String beanClassName);

    Class getBeanClass();

    MutablePropertyValues getPropertyValues();
}
