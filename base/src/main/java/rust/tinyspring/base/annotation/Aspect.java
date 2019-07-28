package rust.tinyspring.base.annotation;

import java.lang.annotation.*;

/**
 * Annotation for employing AoP
 * @author rust
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
