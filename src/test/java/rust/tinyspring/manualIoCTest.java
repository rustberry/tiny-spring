package rust.tinyspring;

import rust.tinyspring.annotation.Controller;
import rust.tinyspring.annotation.Inject;
import rust.tinyspring.util.BeanUtil;
import rust.tinyspring.util.ClassUtil;
import rust.tinyspring.util.utilLoader;
import rust.tinyspring.entity.*;

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
