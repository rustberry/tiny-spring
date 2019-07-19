package rust.tinyspring.util;

/**
 * Load all the static {@code *Util}.
 * @author rust
 */
public final class utilLoader {
    public static void init() {
        // All currently used utils
        Class<?>[] classes = {
                ClassUtil.class,
                BeanUtil.class,
                ReflectionUtil.class,
                IoCUtil.class
        };
        for (Class<?> clz : classes) {
            ClassUtil.loadClass(clz.getName());
        }
    }
}
