package rust.tinyspring.test.entity;

public class Wheel {
    private String brand;
    Wheel() {}

    public void roll() {
        System.out.println(brand + "Wheel rolling...");
    }
}
