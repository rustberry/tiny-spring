package rust.tinyspring.beans.factory.support;

import lombok.extern.slf4j.Slf4j;
import rust.tinyspring.beans.BeanDefinition;
import rust.tinyspring.beans.BeanPostProcessor;
import rust.tinyspring.beans.exception.BeansException;
import rust.tinyspring.beans.factory.BeanFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public abstract class AbstractBeanFactory implements BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    @Override
    public Object getBean(String name) throws BeansException {
        BeanDefinition beanDefinition = getBeanDefinition(name);
        if (beanDefinition == null) {
            log.error("No such Bean: " + name);
            throw new BeansException("No such Bean: " + name);
        }

        return doCreateBean(beanDefinition);
    }

    protected Object doCreateBean(BeanDefinition beanDefinition) {
        // 1. create bean instance
        Object bean = createBeanInstance(beanDefinition);
        // 2. populate bean, inject fields
        populateBean(bean, beanDefinition);
        // 3. initialize
        bean = initializeBean(bean, beanDefinition);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws BeansException {
        try {
            return beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e){
            log.error("newInstance error", e);
            throw new BeansException("newInstance error", e);
        }
    }

    protected void populateBean(Object bean, BeanDefinition beanDefinition) {
        // TODO autowire
        applyPropertyValues(bean, beanDefinition);
    }

    protected Object initializeBean(Object bean, BeanDefinition beanDefinition) {
        Object wrappedBean = bean;
        wrappedBean = applyPostProcessorBeforeInitialization(wrappedBean, beanDefinition.getBeanName());
        // TODO invokeInitMethod
        wrappedBean = applyPostProcessorAfterInitialization(wrappedBean, beanDefinition.getBeanName());
        return wrappedBean;
    }

    private Object applyPostProcessorBeforeInitialization(Object wrappedBean, String beanName) {
        for (BeanPostProcessor beanPostProcessor : this.beanPostProcessorList) {
            wrappedBean = beanPostProcessor.postProcessBeforeInitialization(wrappedBean, beanName);
        }
        return wrappedBean;
    }

    private Object applyPostProcessorAfterInitialization(Object wrappedBean, String beanName) {
        for (BeanPostProcessor beanPostProcessor : this.beanPostProcessorList) {
            wrappedBean = beanPostProcessor.postProcessAfterInitialization(wrappedBean, beanName);
        }
        return wrappedBean;
    }


    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) {
        // TODO
    }

    private void addPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessorList.add(beanPostProcessor);
    }

    protected BeanDefinition getBeanDefinition(String name) {
        return beanDefinitionMap.get(name);
    }
}
