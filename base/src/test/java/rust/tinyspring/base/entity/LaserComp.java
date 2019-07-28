package rust.tinyspring.base.entity;

import rust.tinyspring.base.annotation.Controller;

/**
 * Laser component for steel robot arms.
 */
@Controller
public class LaserComp {
//    LaserComp() {}

    public void spit() {
        System.out.println("Laser ray!");
    }
}
