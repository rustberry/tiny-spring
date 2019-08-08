package rust.tinyspring.beans.factory.support;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import rust.tinyspring.base.util.ClassUtil;
import rust.tinyspring.beans.BeanDefinition;
import rust.tinyspring.beans.exception.BeansException;
import rust.tinyspring.beans.factory.BeanFactory;
import rust.tinyspring.beans.factory.config.ConfigurableBeanFactory;

@Slf4j
public class DefaultBeanFactory extends AbstractBeanFactory implements BeanDefinitionRegistry, ConfigurableBeanFactory {

    /**
     * 存放 bean 的定义，不仅存在 Map 中，BeanDefinition 的子类 GenericBeanDefinition 中也要存储，方便扩展
     */
    private final Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();
    private ClassLoader beanClassLoader = null;

    /**
     * 初始化类定义
     */
    public DefaultBeanFactory() {
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
    public Object createBeanInstance(BeanDefinition beanDefinition) throws BeansException {
        try {
            ClassLoader loader = getClassLoader();
            Class<?> clz = loader.loadClass(beanDefinition.getBeanClassName());
            return clz.newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e){
            log.error("newInstance error", e);
            throw new BeansException("newInstance error", e);
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String id) {
        return beanDefinitionMap.get(id);
    }

    @Override
    public void registerBeanDefinition(String id, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(id, beanDefinition);
    }


    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    @Override
    public ClassLoader getClassLoader() {
        return this.beanClassLoader != null ? this.beanClassLoader : ClassUtil.getClassLoader();
    }
}
