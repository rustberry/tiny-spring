package rust.tinyspring.util;

import java.util.HashMap;
import java.util.Map;

public class BeanUtil {
    private static final Map<Class<?>, Object> beanMap = new HashMap<>();

    static {
        for (Class<?> beanCls : ClassUtil.getBeanClassSet()) {
            Object clsInstance = null;
            clsInstance = ReflectionUtil.newInstance(beanCls);
            beanMap.put(beanCls, clsInstance);
        }
    }

    public static Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }

    public static <T> T getBean(Class<T> cls) {
        if (!beanMap.containsKey(cls)) throw new RuntimeException("Cannot get bean for class: " + cls);
        return (T)beanMap.get(cls);
    }
}
