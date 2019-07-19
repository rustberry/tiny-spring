package rust.tinyspring.test.entity;

import lombok.Data;
import rust.tinyspring.annotation.Inject;

@Data
public class LaserRobot {
    @Inject private Wheel wheel;
    @Inject private LaserArm laserArm;
    LaserRobot() {}

    public void robotRun() {
        wheel.roll();
        laserArm.armFire();
        System.out.println("Robot running!");
    }
}
