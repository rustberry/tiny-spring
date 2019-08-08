package rust.tinyspring.beans;

//import lombok.Getter;
//import lombok.Setter;
import rust.tinyspring.beans.factory.MutablePropertyValues;

public interface BeanDefinition {
    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "prototype";
    public static final String SCOPE_DEFAULT = "";

    boolean isSingleton();
    boolean isPrototype();

    String getScope();
    void setScope(String scope);

    String getBeanName();
    void setBeanName(String beanName);

    String getBeanClassName();
    void setBeanClassName(String beanClassName);

    Class getBeanClass();

    MutablePropertyValues getPropertyValues();
}
