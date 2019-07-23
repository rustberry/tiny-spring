package rust.tinyspring.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rust.tinyspring.annotation.Aspect;
import rust.tinyspring.proxy.AbstractAspectProxy;
import rust.tinyspring.proxy.Proxy;
import rust.tinyspring.proxy.ProxyChain;
import rust.tinyspring.proxy.ProxyManager;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@code Target}: a class whose method is to be intercepted.
 * {@code Proxy}: a class to intercept a target's method.
 * 1. Get all proxy classes
 * 2. From their annotation's value (of Annotation.class) get all target classes annotated by it
 * 3. Create proxy instance for target classes
 * 4.Replace the original bean of the target class in the BeanMap with the proxy instance.
 */
public class AoPUtil {
    private static final Logger logger = LoggerFactory.getLogger(AoPUtil.class);

    public static void init() {
        try {
            for (Map.Entry<Class<?>, List<Proxy>> entry : getTargetProxyChainMap().entrySet()) {
                Class<?> targetClass = entry.getKey();
                List<Proxy> proxies = entry.getValue();
                Object proxy = ProxyManager.createProxy(targetClass, proxies);
                BeanUtil.setBean(targetClass, proxy);
            }
        } catch (Exception e) {
            logger.error("AoP failed", e);
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @return all class that is annotated by {@code Aspect} and is subclass of {@code AbstractAspectProxy}
     */
    private static Set<Class<?>> getProxyClassSet() {
        Set<Class<?>> aspectClassSet = ClassUtil.getClassSetByAnnotation(Aspect.class);
        for (Class<?> cls : aspectClassSet) {
            if (cls.isAssignableFrom(AbstractAspectProxy.class) && !cls.equals(Aspect.class)) {
                aspectClassSet.add(cls);
            }
        }
        return aspectClassSet;
    }

    /**
     * Note that for one proxy, there may be more than one target class to it.
     * @return a map of proxy class and its target classes
     */
    private static Map<Class<?>, Set<Class<?>>> getProxyTargetMap() {
        Map<Class<?>, Set<Class<?>>> proxyTargetMap = new HashMap<>();
        Set<Class<?>> aspectClassSet = getProxyClassSet();
        for (Class<?> cls : aspectClassSet) {
            Class<? extends Annotation> proxyAnnotation = cls.getAnnotation(Aspect.class).value();
            Set<Class<?>> annotatedByProxy = ClassUtil.getClassSetByAnnotation(proxyAnnotation);
            proxyTargetMap.put(cls, annotatedByProxy);
        }
        return proxyTargetMap;
    }

    private static Map<Class<?>, List<Proxy>> getTargetProxyChainMap() {
        Map<Class<?>, List<Proxy>> targetProxyChainMap = new HashMap<>();
        Map<Class<?>, Set<Class<?>>> proxyTargetMap = getProxyTargetMap();

        for (Map.Entry<Class<?>, Set<Class<?>>> entry : proxyTargetMap.entrySet()) {
            Class<?> proxyClass = entry.getKey();
            Set<Class<?>> targetSet = entry.getValue();

            for (Class<?> targetClass : targetSet) {
                Proxy p = (Proxy) ReflectionUtil.newInstance(proxyClass);
                if (targetProxyChainMap.containsKey(targetClass)) {
                    targetProxyChainMap.get(targetClass).add(p);
                } else {
//                    List<Proxy> proxyChain = new ArrayList<>(Arrays.asList(p));
                    List<Proxy> proxies = Stream.of(p).collect(Collectors.toList());
                    targetProxyChainMap.put(targetClass, proxies);
                }
            }
        }

        return targetProxyChainMap;
    }
}
