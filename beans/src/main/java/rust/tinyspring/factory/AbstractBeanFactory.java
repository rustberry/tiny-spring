package rust.tinyspring.factory;

import rust.tinyspring.BeanDefinition;
import rust.tinyspring.BeanPostProcessor;
import rust.tinyspring.exception.BeanException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    @Override
    public Object getBean(String name) throws BeanException {
        BeanDefinition beanDefinition = getBeanDefinition(name);
        if (beanDefinition == null) {
            throw new BeanException("No such Bean: " + name);
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

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws BeanException {
        try {
            return beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e){
            throw new BeanException("newInstance error", e);
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

    private BeanDefinition getBeanDefinition(String name) {
        return beanDefinitionMap.get(name);
    }
}
