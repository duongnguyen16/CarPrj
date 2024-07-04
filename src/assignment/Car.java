package assignment;

public class Car {
    private String carID, frameID, engineID, color, brand;

    public Car() {

    }

    public Car(String carID, String frameID, String engineID, String color, String brand) {
        this.carID = carID;
        this.frameID = frameID;
        this.engineID = engineID;
        this.color = color;
        this.brand = brand;
    }

    // getter

    public String getCarID() {
        return carID;
    }

    public String getFrameID() {
        return frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public String getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    // setter

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carID='" + carID + '\'' +
                ", frameID='" + frameID + '\'' +
                ", engineID='" + engineID + '\'' +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }

    public int compareTo(Car car) {
        return this.carID.compareTo(car.carID);
    }

    public String screenString() {
        return String.format("%-10s%-10s%-10s%-10s%-10s", carID, frameID, engineID, color, brand);
    }
}
