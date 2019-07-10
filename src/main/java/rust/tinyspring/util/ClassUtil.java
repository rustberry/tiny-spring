package rust.tinyspring.util;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;


public class ClassUtil {
    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);
     private static String basePkg;

    /**
     *
     * @return ClassLoader of current thread
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     *
     * @param className name of the class
     * @param isInitialized whether it's been initialized
     * @return an instance of that class
     */
    public static Class<?> loadClass(String className, boolean isInitialized) throws ClassNotFoundException {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
            return cls;
        } catch (ClassNotFoundException e) {
            logger.error("Load class failure", e);
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Get all the classes in a package
     * @param pkgName name of the package
     * @return a Set containing all classes
     */
    public static Set<Class<?>> getClassSet(String pkgName) {
        Set<Class<?>> classSet = new HashSet<>();
        // Todo
        return classSet;
    }

    public static Set<Class<?>> getBeanClassSet(Class<? extends Annotation>... cls) {
        Set<Class<?>> classSet = new HashSet<>();

        for (Class<?> c : getClassSet(basePkg)) {
            for (Class<? extends Annotation> annotationCls : cls) {
                if (c.isAnnotationPresent(annotationCls)) {
                    classSet.add(c);
                }
            }
        }
        return classSet;
    }
}
