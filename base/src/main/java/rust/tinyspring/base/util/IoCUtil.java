package rust.tinyspring.base.util;

import rust.tinyspring.base.annotation.Inject;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 1. get BeanMap of (class, instance) pairs 2. iterate, and for each, get {@code Field}
 * 3. if the field is annotated as {@code Inject}: get class by {@code getType} -> get the instance from BeanMap
 * 4. {@code setField}
 */
public class IoCUtil {
    public static void init() {
        Map<Class<?>, Object> beanMap = BeanUtil.getBeanMap();
        if (!beanMap.isEmpty()) {
            for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
                Class<?> beanClass = entry.getKey();
                Object beanInstance = entry.getValue();
                // Get all fields of the class
                // Note not to use {@code getFields} which only returns public fields
                Field[] beanFields = beanClass.getDeclaredFields();
                for (Field field : beanFields) {
                    if (field.isAnnotationPresent(Inject.class)) {
                        Class<?> fieldClass = field.getType();
                        // Get the instance from beanMap
                        Object fieldInstance = beanMap.get(fieldClass);
                        if (beanInstance != null) {
                            // Set the field of beanInstance. **Inject Dependency**.
                            ReflectionUtil.setField(beanInstance, field, fieldInstance);
                        }
                    }
                }
            }
        }
    }
}
