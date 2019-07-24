package rust.tinyspring.entity;

import lombok.Getter;
import rust.tinyspring.annotation.Controller;
import rust.tinyspring.annotation.Inject;
import rust.tinyspring.annotation.Laser;

@Controller
@Laser
public class LaserArm {
    private String brand;
    @Inject
    @Getter
    private LaserComp laserComp;

//    LaserArm() {}
//    LaserArm(LaserComp lc) {
//        this.laserComp = lc;
//    }

    public void armFire() {
        System.out.println("A powerful laser arm!");
        laserComp.spit();
    }
}
