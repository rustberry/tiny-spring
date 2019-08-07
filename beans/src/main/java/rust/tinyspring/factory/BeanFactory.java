package rust.tinyspring.factory;

import rust.tinyspring.BeanDefinition;

public interface BeanFactory {
    Object getBean(String name);
}
