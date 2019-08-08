package rust.tinyspring.base.util;

public class Assert {

    public static void notNull(Object argument, String msg) {
        if (argument == null) throw new IllegalArgumentException(msg);
    }

    public static void notNull(Object argument) {
        notNull(argument, "Argument: " + argument + " must not be null");
    }
}
