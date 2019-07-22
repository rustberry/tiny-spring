package rust.tinyspring.annotation;

import rust.tinyspring.proxy.Proxy;
import rust.tinyspring.proxy.ProxyChain;

import java.lang.reflect.Method;

/**
 * Use template method.
 */
public abstract class AbstractAspectProxy implements Proxy {

    @Override
    public final Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;

        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();

        begin();
        try {
            if (intercept(cls, method, params)) {
                before(cls, method, params);
                result = proxyChain.doProxyChain();
                after(cls, method, params);
            }
        } finally {
            end();
        }

        return result;
    }

    public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable{
        return true;
    }

    public void begin() {}

    public void end() {}

    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {}

    public void after(Class<?> cls, Method method, Object[] params) throws Throwable {}
}
