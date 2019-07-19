package rust.tinyspring.util;

/**
 * Load all the static {@code *Util}.
 * @author rust
 */
public final class utilLoader {
    public static void init() {
        // All currently used utils
        Class<?>[] classes = {
                BeanUtil.class,
                ClassUtil.class,
                IoCUtil.class,
                PropUtil.class,
                ReflectionUtil.class
        };
        for (Class<?> clz : classes) {
            ClassUtil.loadClass(clz.getName());
        }
    }
}
