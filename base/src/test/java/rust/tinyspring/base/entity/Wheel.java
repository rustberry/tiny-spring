package rust.tinyspring.base.entity;

import rust.tinyspring.base.annotation.Controller;

@Controller
public class Wheel {
    private String brand = "";
    public Wheel() {}

    public void roll() {
        System.out.println(brand + "Wheel rolling...");
    }
}
