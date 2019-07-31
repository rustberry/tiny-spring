package rust.tinyspring;

import rust.tinyspring.exception.BeanException;

public interface BeanPostProcessor {

    /**
     * @param bean the new bean instance
     * @return the bean instance to use, either the original or a wrapped one;
     * if null, no subsequent BeanPostProcessors will be invoked.
     * 返回供使用的 bean 实例，可能是原来的 bean，也可能是被代理过的
     * @throws BeanException
     */
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }

    default Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException{
        return bean;
    }
}
