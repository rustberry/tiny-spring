package rust.tinyspring.base.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rust.tinyspring.base.annotation.Aspect;
import rust.tinyspring.base.annotation.Laser;
import rust.tinyspring.base.proxy.AbstractAspectProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect(Laser.class)
public class LaserArmAspect extends AbstractAspectProxy {
    private static final Logger logger = LoggerFactory.getLogger(LaserArmAspect.class);

    @Override
    public void begin() {
        System.out.println("Hello from LaserArm Aspect at teh beginning");
    }

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable{
        logger.debug("------- Before method --------");
        logger.debug("cls: "+cls);
        logger.debug("method: "+method);
        logger.debug("params: "+ Arrays.toString(params));
    }
}
