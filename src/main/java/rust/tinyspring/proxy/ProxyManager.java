package rust.tinyspring.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.util.List;

public class ProxyManager {

    /**
     * Create the proxy instance of the target class {@code targetClass}. Using CGLib,
     * it will be a subclass or subinterface of the target class.
     * @param targetClass the class object of the target class
     * @param proxyList all proxy instances for the target class
     * @param <T> the type of {@code targetClass}
     * @return a proxy instance of the target class
     */

    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList) {
        // This callback will be invoked when target's intercepted method is invoked
        MethodInterceptor callback = (targetObject, targetMethod, methodParams, methodProxy)
                -> new ProxyChain(targetClass, targetObject, targetMethod, methodProxy, methodParams, proxyList).doProxyChain();
        // To have finer control over instantiation could use the non-static method
        @SuppressWarnings("unchecked") T t = (T) Enhancer.create(targetClass, callback);
        return t;
    }
}
