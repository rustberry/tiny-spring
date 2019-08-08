package rust.tinyspring.context.support;

import rust.tinyspring.beans.factory.support.DefaultBeanFactory;
import rust.tinyspring.core.io.FileSystemResource;
import rust.tinyspring.core.io.Resource;

public class FileSystemXmlApplicationContext extends AbstractApplicationContext {
    private DefaultBeanFactory factory;

    public FileSystemXmlApplicationContext(String configFileName) {
        super(configFileName);
    }

    @Override
    protected Resource getResource(String configFileName) {
        return new FileSystemResource(configFileName);
    }
}
