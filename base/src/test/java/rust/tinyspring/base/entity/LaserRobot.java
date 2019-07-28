package rust.tinyspring.base.entity;

import lombok.Data;
import rust.tinyspring.base.annotation.Controller;
import rust.tinyspring.base.annotation.Inject;

@Controller
@Data
public class LaserRobot {
    @Inject private Wheel wheel;
    @Inject private LaserArm laserArm;
//    LaserRobot() {}

    public void robotRun() {
        wheel.roll();
        laserArm.armFire();
        System.out.println("Robot running!");
    }
}
