package rust.tinyspring.entity;

import rust.tinyspring.annotation.Controller;

@Controller
public class Wheel {
    private String brand = "";
    public Wheel() {}

    public void roll() {
        System.out.println(brand + "Wheel rolling...");
    }
}
