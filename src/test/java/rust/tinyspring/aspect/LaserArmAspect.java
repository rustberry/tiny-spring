package rust.tinyspring.aspect;

import rust.tinyspring.annotation.Aspect;
import rust.tinyspring.annotation.Laser;
import rust.tinyspring.proxy.AbstractAspectProxy;

@Aspect(Laser.class)
public class LaserArmAspect extends AbstractAspectProxy {

    @Override
    public void begin() {
        System.out.println("Hello from LaserArm Aspect");
    }
}
