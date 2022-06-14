package entity;

/*
 *@Author WTF
 *@Date 2022/6/14 15:40
 */
public class Car {

    private String brand;

    private int doors;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", doors=" + doors +
                '}';
    }
}
