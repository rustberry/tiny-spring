package rust.tinyspring.util;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * Currently, this only returns new instance of classes that is accessible to this class
     * and its nullary constructor accessible too.
     * @return new instance of the class
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Object newInstance(Class<?> cls, boolean useCglib) {
        if (useCglib) {
            return newInstanceByCglib(cls);
        } else {
            return newInstance(cls);
        }
    }

    /**
     * Currently, this only returns new instance of classes that is accessible to this class
     * and its nullary constructor accessible too.
     * @param cls the type of the instantiated object
     * @return new instance of the type {@code cls}
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Object newInstance(Class<?> cls) {
        Object instance = null;
        try {
            instance = cls.newInstance();
        } catch (IllegalAccessException e) {
            logger.error("The class or its nullary constructor is not accessible", e);
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            logger.error("Instantiation exception", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * Use CgLib to instantiate a class.
     * @param cls the type of the instantiated object
     * @return new instance of the type {@code cls}
     */
    public static Object newInstanceByCglib(Class<?> cls) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallback(NoOp.INSTANCE);
        return enhancer.create();
        // Todo return (T) enhancer.create(ctr.getParameterTypes(),args);

    }

    public static Object invokeMethod(Object target, Method method, Object ...args) throws IllegalAccessException, InvocationTargetException {
        Object ret = null;
        try {
            method.setAccessible(true);
            ret = method.invoke(target, args);
        } catch (IllegalAccessException e) {
            logger.error("Access exception", e);
            throw e;
        } catch (InvocationTargetException e) {
            logger.error("", e);
            throw e;
        }
        return ret;
    }

    public static void setField(Object target, Field field, Object val) {
        try {
            field.setAccessible(true);
            field.set(target, val);
        } catch (SecurityException e) {
            logger.error("setAccessible failed", e);
            throw e;
        } catch (IllegalAccessException e) {
            logger.error("illegal access when set field", e);
        }
    }
}
