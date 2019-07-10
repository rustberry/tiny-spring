package rust.tinyspring.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     *
     * @param cls
     * @return new instance of the class
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Object newInstance(Class<?> cls) {
        Object instance = null;
        try {
            instance = cls.newInstance();
        } catch (IllegalAccessException e) {
            logger.error("Access exception", e);
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            logger.error("Instantiation exception", e);
            throw new RuntimeException(e);
        }
        return instance;
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
