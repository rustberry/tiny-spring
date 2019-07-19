package rust.tinyspring.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import rust.tinyspring.annotation.Inject;
import rust.tinyspring.test.entity.LaserRobot;
import rust.tinyspring.util.utilLoader;

/**
 * Unit test for IoC functionality (i.e. the {@Inject} annotation), using POJO only.
 * @author rust
 */
public class IocInjectTest {
    @Inject
    private LaserRobot laserRobot;

    @Before
    public void init() {
        utilLoader.init();
    }

    @Test
    public void injectIocTest() {

        Assert.assertTrue(laserRobot != null);
        Assert.assertTrue(laserRobot.getWheel() != null);
        Assert.assertTrue(laserRobot.getLaserArm() != null);
        Assert.assertTrue(laserRobot.getLaserArm().getLaserComp() != null);
    }
}
