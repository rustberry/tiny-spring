package rust.tinyspring.base;

import rust.tinyspring.base.annotation.Controller;
import rust.tinyspring.base.annotation.Inject;
import rust.tinyspring.base.util.BeanUtil;
import rust.tinyspring.base.util.ClassUtil;
import rust.tinyspring.base.util.utilLoader;
import rust.tinyspring.base.entity.*;

import java.util.Map;

@Controller  // Any class marked as {@code Controller} will be loaded at init time
public class manualIoCTest {
    @Inject
    private static LaserRobot laserRobot;

    public static void main(String[] args) {
//        ClassUtil.setBasePkg("rust.spring.entity");
        utilLoader.init();
//        Map<Class<?>, Object> bm = BeanUtil.getBeanMap();

//        LaserRobot laserRobot = (LaserRobot) bm.get(LaserRobot.class);
        if (laserRobot == null) {
            System.out.println("LaserRobot null");
        } else {
            if (laserRobot.getLaserArm() == null) System.out.println("LaserArm null");
            else {
                if (laserRobot.getLaserArm().getLaserComp() == null) System.out.println("LaserComp null");
                else laserRobot.robotRun();
            }
        }
    }
}
