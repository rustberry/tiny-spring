package rust.tinyspring.test.entity;

import lombok.Getter;
import rust.tinyspring.annotation.Inject;

public class LaserArm {
    private String brand;
    @Inject
    @Getter
    private LaserComp laserComp;

    LaserArm() {}
    LaserArm(LaserComp lc) {
        this.laserComp = lc;
    }

    public void armFire() {
        System.out.println("A powerful laser arm!");
        laserComp.spit();
    }
}
