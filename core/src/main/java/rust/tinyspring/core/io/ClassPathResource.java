package rust.tinyspring.core.io;

import rust.tinyspring.base.util.ClassUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource {
    private String fileName;
    private ClassLoader classLoader;

    public ClassPathResource(String fileName) {
        this(fileName, null);
    }

    public ClassPathResource(String fileName, ClassLoader classLoader) {
        this.fileName = fileName;
        this.classLoader = classLoader == null ? ClassUtil.getClassLoader() : classLoader;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(fileName);
        if (is == null) {
            throw new FileNotFoundException("File: " + fileName + " not Found");
        }
        return is;
    }

    @Override
    public String getDescription() {
        return this.fileName;
    }
}
