package rust.tinyspring.beans.factory.support.xml;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import rust.tinyspring.beans.BeanDefinition;
import rust.tinyspring.beans.exception.BeansException;
import rust.tinyspring.beans.factory.support.BeanDefinitionRegistry;
import rust.tinyspring.beans.factory.support.DefaultFactory;
import rust.tinyspring.beans.factory.support.GenericBeanDefinition;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

@Slf4j
public class XmlBeanDefinitionReader {
    /**
     * 定义 xml 中 <bean> 标签的属性
     */
    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(DefaultFactory factory) {
        this.beanDefinitionRegistry = factory;
    }

    /**
     * 解析传进来的 xml 文件
     *
     * 将当前配置文件中所有的 bean 存放在 beanDefinitionMap 中
     * 当需要 getBean 获取实例时，通过 BeanDefinition 获得className
     * 通过反射创建实例
     * @param configFile
     */
    public void loadBeanDefinition(String configFile) {
        InputStream inputStream = null;
        try {
            // 获得类加载器
            ClassLoader classLoader = this.getClass().getClassLoader();
            // 通过类加载器获得文件输入流
            inputStream = classLoader.getResourceAsStream(configFile);


            // 创建 dom4j 对象
            SAXReader saxReader = new SAXReader();

            // 获得文件对象
            Document document = saxReader.read(inputStream);
            Element root = document.getRootElement();
            // 将当前 bean xml 文件中的所有 bean
            Iterator<Element> it = root.elementIterator();
            while (it.hasNext()) {
                Element el = it.next();
                String beanID = el.attributeValue(ID_ATTRIBUTE);
                String beanClassName = el.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(beanID, beanClassName);
//                this.beanDefinitionMap.put(beanID, beanDefinition);
                beanDefinitionRegistry.registerBeanDefinition(beanID, beanDefinition);
            }
        } catch (DocumentException e) {
            log.error("IOException parsing XML document failed", e);
            throw new BeansException("IOException parsing XML document failed", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadBeanDefinition4Test(String fileName) {
//        String fileName = "petStrore-v1.xml";
        ClassLoader cl = null;
//        cl = Thread.currentThread().getContextClassLoader();
        cl = this.getClass().getClassLoader();

//        String AbsPath = cl.getResource("").getPath() + fileName;
//        InputStream is2 = cl.getResourceAsStream(AbsPath);
//        is2 = DefaultFactory.class.getResourceAsStream(AbsPath);

        InputStream is = null;
        is = cl.getResourceAsStream(fileName);
//        assertNotNull(is);
        if (is == null) throw new RuntimeException("stream is null");

        StringBuilder text = new StringBuilder();
        byte[] buf = new byte[256];
        int len = 0;


        try {
            while((len = is.read(buf)) != -1) {
                text.append(new String(buf, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(text);
    }
}
