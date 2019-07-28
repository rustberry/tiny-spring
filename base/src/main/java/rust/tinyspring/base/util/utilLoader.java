package rust.tinyspring.base.util;

/**
 * Load all the static {@code *Util}.
 * @author rust
 */
public final class utilLoader {
    public static void init() {
        // All currently used utils
        Class<?>[] classes = {
                BeanUtil.class,
        };
        for (Class<?> clz : classes) {
            ClassUtil.loadClass(clz.getName());
        }
        AoPUtil.init();
        IoCUtil.init();
    }
}
