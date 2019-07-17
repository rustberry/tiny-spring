package rust.tinyspring.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rust.tinyspring.ConstConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Rust
 */
public class PropUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropUtil.class);
    public static Properties prop = loadProp();

    public static Properties loadProp() {
        Properties p = new Properties();
        try {
            InputStream is = ClassUtil.getClassLoader().getResourceAsStream(ConstConfig.ConfigFile);
            p.load(is);

        } catch (IOException e) {
            logger.error("Error loading properties");
            throw new RuntimeException(e);
        }
        return p;
    }

    public static String getBasePkg() {
        String basePkg = ConstConfig.AppBasePackage;
        return getString(prop, basePkg);
    }

    public static String getString(Properties prop, String key) {
        String dflt = "";
        if (key == null || key.equals("")) return dflt;
        return (String) prop.get(key);
    }

    public static int getInt(Properties prop, String key) {
        int dflt = 0;
        if (key == null || key.equals("")) return dflt;
        return (int) prop.get(key);
    }
}
