package rust.tinyspring.base;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import rust.tinyspring.base.annotation.Controller;
import rust.tinyspring.base.annotation.Inject;
import rust.tinyspring.base.entity.LaserRobot;
import rust.tinyspring.base.util.ClassUtil;
import rust.tinyspring.base.util.utilLoader;

/**
 * Unit test for IoC functionality (i.e. the {@code Inject} annotation), using POJO only.
 * @author rust
 */
//@Controller
public class IocInjectTest {
    @Inject
    private LaserRobot laserRobot;

    @Before
    public void init() {
        utilLoader.init();
    }

    @Test
    public void injectIocTest() {

        Assert.assertNotNull(laserRobot);
        Assert.assertNotNull(laserRobot.getWheel());
        Assert.assertNotNull(laserRobot.getLaserArm());
        Assert.assertNotNull(laserRobot.getLaserArm().getLaserComp());
    }
}
