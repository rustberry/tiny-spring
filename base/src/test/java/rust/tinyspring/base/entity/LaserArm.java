package rust.tinyspring.base.entity;

import lombok.Getter;
import rust.tinyspring.base.annotation.Controller;
import rust.tinyspring.base.annotation.Inject;
import rust.tinyspring.base.annotation.Laser;

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
