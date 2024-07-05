package assignment;

import java.util.*;
import java.io.*;

public class CarList extends ArrayList<Car> {
    BrandList bList = new BrandList();

    public CarList() {
        super();
    }

    public CarList(
            BrandList bList) {
        super();
        this.bList = bList;
    }

    public String toString() {
        /*
         * Associating fields to a string for writing a car to file
         * Return format < carID, brand.brandID, color, frameID, engineID>
         * 
         */
        String res = "";
        for (Car car : this) {
            res += car.getCarID() + "," + car.getBrand() + "," + car.getColor() + "," + car.getFrameID() + ","
                    + car.getEngineID() + "\n";
        }
        return res;
    }

    public String screenString() {
        /*
         * Associating fields to a string for outputting a car to screen
         * Return format < brand, “\n”, car_ID, color, frameID, engineID>
         * 
         * 
         */
        String res = "";
        for (Car car : this) {
            res += car.getBrand() + "\n" + car.getCarID() + "," + car.getColor() + "," + car.getFrameID() + ","
                    + car.getEngineID() + "\n";
        }
        return res;
    }

    public boolean loadFromFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            return false;
        } else {
            // open file reading line by line
            try {
                Scanner s = new Scanner(f);
                while (s.hasNextLine()) {
                    String line = s.nextLine();
                    String[] parts = line.split(",");
                    Car car = new Car(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    this.add(car);
                }
                s.close();
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }

        }
    }

    public boolean saveToFile(String filename) {
        File f = new File(filename);
        for (Car car : this) {
            try {
                FileWriter fw = new FileWriter(f, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                pw.print(car.getCarID() + "," + car.getBrand() + "," + car.getColor() + "," + car.getFrameID() + ","
                        + car.getEngineID() + "\n");
                pw.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public int searchID(String carID) {
        int n = this.size();
        for (int i = 0; i < n; i++) {
            if (this.get(i).getCarID() == carID) {
                return i;
            }
        }
        return -1;
    }

    public int searchFrame(String fID) {
        int n = this.size();
        for (int i = 0; i < n; i++) {
            if (this.get(i).getFrameID() == fID) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String eID) {
        int n = this.size();
        for (int i = 0; i < n; i++) {
            if (this.get(i).getEngineID() == eID) {
                return i;
            }
        }
        return -1;
    }

    public static boolean checkFormat(String input, String type) {
        // type: F0000 or E0000 type = "F" or "E"

        if (type == "F") {
            if (input.length() != 5) {
                return false;
            }
            if (input.charAt(0) != 'F') {
                return false;
            }
            for (int i = 1; i < 5; i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else if (type == "E") {
            if (input.length() != 5) {
                return false;
            }
            if (input.charAt(0) != 'E') {
                return false;
            }
            for (int i = 1; i < 5; i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public void addCar() {
        /*
         * Receive carID, carID must be not duplicated
         * Create a menu for choosing a brand;
         * Band b = (Brand)menu. ref_getChoice(brandList);
         * Receive color, color can not be blank
         * Receive frameID. It must be in the “F0000” and not be duplicated
         * Receive engineID. It must be in the “E0000” format and not be duplicated
         * Create a new car with inputted data;
         * Add a new car to the list
         * 
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter car ID: ");
        String carID = sc.nextLine();
        if (searchID(carID) != -1) {
            System.out.println("Car ID already exists");
            sc.close();
            return;
        }
        Brand b = bList.getUserChoice();
        System.out.println("Enter color: ");
        String color = sc.nextLine();
        if (color.isBlank()) {
            System.out.println("Color can not be blank");
            sc.close();
            return;
        }
        System.out.println("Enter frame ID: ");
        String frameID = sc.nextLine();
        if (!checkFormat(frameID, "F")) {
            System.out.println("Frame ID must be in the F0000 format");
            sc.close();
            return;
        }
        if (searchFrame(frameID) != -1) {
            System.out.println("Frame ID already exists");
            sc.close();
            return;
        }
        System.out.println("Enter engine ID: ");
        String engineID = sc.nextLine();
        if (!checkFormat(engineID, "E")) {
            System.out.println("Engine ID must be in the E0000 format");
            sc.close();
            return;
        }
        if (searchEngine(engineID) != -1) {
            System.out.println("Engine ID already exists");
            sc.close();
            return;
        }
        Car temp = new Car(carID, frameID, engineID, color, b.getBrandID());
        this.add(temp);
        sc.close();

    }

    public void printBasedBrandName(String inp) {
        int n = this.size();
        int count = 0;
        for (int i = 0; i < n; i++) {

            Car c = this.get(i);
            // debug
            System.out.println("DEBUG> printBasedBrandName: " + c.getBrand() + " <searching: " + inp + ">");
            if (c.getBrand().contains(inp)) {
                System.out.println(c.screenString());
                count++;
            }
        }
        if (count == 0)
            System.out.println("No car is detected!");
    }

    public boolean removeCar(String id) {
        // based on carID
        int index = searchID(id);
        if (index == -1) {
            System.out.println("Not found");
            return false;
        } else {
            this.remove(index);
        }
        return true;
    }

    public boolean updateCar(String id) {
        // based on carID
        int index = searchID(id);
        if (index == -1) {
            System.out.println("Not found");
            return false;
        } else {
            /*
             * Create a menu for choosing a brand;
             * Band b = (Brand)menu. ref_getChoice(brandList);
             * Receive color, color can not be blank
             * Receive frameID. It must be in the “F0000” and not be duplicated
             * Receive engineID. It must be in the “E0000” format and not be duplicated
             * 
             */
            Car temp = this.get(index);
            Brand b = bList.getUserChoice();
            temp.setBrand(b.getBrandID());
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter color: ");
            temp.setColor(sc.nextLine());
            if (temp.getColor().isBlank()) {
                System.out.println("Color can not be blank");
                sc.close();
                return false;
            }
            System.out.println("Enter frame ID: ");
            temp.setFrameID(sc.nextLine());
            if (!checkFormat(temp.getFrameID(), "F")) {
                System.out.println("Frame ID must be in the F0000 format");
                sc.close();
                return false;
            }
            if (searchFrame(temp.getFrameID()) != -1) {
                System.out.println("Frame ID already exists");
                sc.close();
                return false;
            }
            System.out.println("Enter engine ID: ");
            temp.setEngineID(sc.nextLine());
            if (!checkFormat(temp.getEngineID(), "E")) {
                System.out.println("Engine ID must be in the E0000 format");
                sc.close();
                return false;
            }
            if (searchEngine(temp.getEngineID()) != -1) {
                System.out.println("Engine ID already exists");
                sc.close();
                return false;
            }
            this.set(index, temp);
            sc.close();
        }
        return true;
    }

    public void listCars() {
        System.out.println("DEBUG> List of cars: ");
        int n = this.size();
        for (int i = 0; i < n; i++) {
            Car c = this.get(i);
            System.err.println(c.screenString());
        }
    }

}
