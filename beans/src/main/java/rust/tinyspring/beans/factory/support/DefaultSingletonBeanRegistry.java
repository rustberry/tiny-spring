package rust.tinyspring.beans.factory.support;

import rust.tinyspring.base.util.Assert;
import rust.tinyspring.beans.exception.BeansException;
import rust.tinyspring.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        Assert.notNull(beanName);

        Object oldSingleton = getSingleton(beanName);
        if (oldSingleton != null) {
            throw new BeansException("There has already been an object: " + oldSingleton);
        }
        this.singletonObjects.put(beanName, singletonObject);
    }

    @Override
    public Object getSingleton(String beanName) {
        return this.singletonObjects.get(beanName);
    }
}
