package assignment;

public class Car {
    private String carID, frameID, engineID, color, brand;

    public Car() {

    }

    // <ID, brand ID, color, frame ID, engine ID>
    public Car(String carID, String brandID, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brandID;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
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

    public int compareTo(Car c) {
        int diff = this.brand.compareTo(c.brand);
        if (diff != 0) {
            return diff;
        }
        else {
            return this.carID.compareTo(c.carID);
        }
    }

    public String screenString() {
        return String.format("%-10s%-10s%-10s%-10s%-10s", carID, brand, color, frameID, engineID);
    }
}
