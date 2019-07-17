package rust.tinyspring.util;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import rust.tinyspring.annotation.Controller;
import rust.tinyspring.annotation.Inject;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class ClassUtil {
    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);
    private static String basePkg;

    /**
     * Returns a set containing all classes annotated as Bean.
     * Currently includes {@code Controller} and {@code Inject}
     * @return
     */
    public static Set<Class<?>> getBeanClassSet() {
        return getBeanClassSet(Inject.class, Controller.class);
    }

    /**
     * Returns a set containing all classes annotated as Bean.
     * Currently includes {@code Controller} and {@code Inject}
     * @param cls Variable args of {@code classes} that are defined as Bean
     * @return
     */
    public static Set<Class<?>> getBeanClassSet(Class<? extends Annotation>... cls) {
        Set<Class<?>> classSet = new HashSet<>();

        for (Class<?> c : getClassSet(basePkg)) {
            for (Class<? extends Annotation> annotationCls : cls) {
                if (c.isAnnotationPresent(annotationCls)) {
                    classSet.add(c);
                }
            }
        }
        return classSet;
    }


    /**
     * Returns the ClassLoader of the current thread
     * @return ClassLoader of current thread
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * Returns an instance of the given className
     * @param className name of the class
     * @return an instance of that class
     */
    public static Class<?> loadClass(String className) throws ClassNotFoundException {
        return loadClass(className, false);
    }

    /**
     * Returns an instance of the given className
     * @param className name of the class
     * @param isInitialized whether it's been initialized
     * @return an instance of that class
     */
    public static Class<?> loadClass(String className, boolean isInitialized) throws ClassNotFoundException {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
            return cls;
        } catch (ClassNotFoundException e) {
            logger.error("Load class failure", e);
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Get all the classes in a package
     * @param pkgName name of the package
     * @return a Set containing all classes
     */
    public static Set<Class<?>> getClassSet(String pkgName) {
        Set<Class<?>> classSet = new HashSet<>();
        try {
            // All files in the directory will be got
            Enumeration<URL> urls = getClassLoader().getResources(pkgName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    logger.trace("url: ",url,"protocol: ", protocol);
                    if (protocol.equals("file")) {
                        // In case file name contains whitespace
//                        String packagePath = url.getPath().replaceAll("%20", " ");
                        addClassInPath(classSet, url.toURI(), pkgName);
                    } else if (protocol.equals("jar")) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while (jarEntries.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("get class set failure", e);
            throw new RuntimeException(e);
        }

        return classSet;
    }

    /**
     *
     */
    public static void addClassInPath(Set<Class<?>> classSet, URI uri, String pkgName) {
//        String pkgPath = url.getPath();
        File[] files = new File(uri).listFiles((file) ->
                (file.isFile() && file.getName().endsWith("class")) || file.isDirectory());
        if (files != null) {
            for (File f : files) {
                if (f.isFile()) {
                    String fileName = f.getName();
                    String className = fileName.substring(0, fileName.lastIndexOf("."));
                    className = pkgName + "." + className;
                    doAddClass(classSet, className);
                } else {
                    String direcName = f.getName();
                    String subPkgName = pkgName + "." + direcName;
                    URI u = f.toURI();
                    addClassInPath(classSet, u, subPkgName);
                }
            }
        }
    }

    public static void doAddClass(Set<Class<?>> classSet, String className) {
        try {
            Class clz = loadClass(className);
            classSet.add(clz);
        } catch (ClassNotFoundException e) {
            logger.error("Error adding class by name");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        System.out.println();
        getClassSet("rust.tinyspring");
    }
}
