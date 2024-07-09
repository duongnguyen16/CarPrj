package assignment;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

        String res = "";
        for (Car car : this) {
            res += car.getCarID() + "," + car.getBrand() + "," + car.getColor() + "," + car.getFrameID() + ","
                    + car.getEngineID() + "\n";
        }
        return res;
    }

    public String screenString() {

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
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (Car car : this) {
                pw.print(car.getCarID() + "," + car.getBrand() + "," + car.getColor() + "," + car.getFrameID() + ","
                        + car.getEngineID() + "\n");
            }
            pw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int searchID(String carID) {
        int n = this.size();
        for (int i = 0; i < n; i++) {

            if (this.get(i).getCarID().equals(carID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchFrame(String fID) {
        int n = this.size();
        for (int i = 0; i < n; i++) {

            if (this.get(i).getFrameID().equals(fID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String eID) {
        int n = this.size();
        for (int i = 0; i < n; i++) {

            if (this.get(i).getEngineID().equals(eID)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean checkFormat(String input, String type) {

        if (type == "F") {

            final Pattern pattern = Pattern.compile("F\\d\\d\\d\\d\\d", Pattern.CASE_INSENSITIVE);
            final Matcher matcher = pattern.matcher(input);
            return matcher.matches();
        } else if (type == "E") {

            final Pattern pattern = Pattern.compile("E\\d\\d\\d\\d\\d", Pattern.CASE_INSENSITIVE);
            final Matcher matcher = pattern.matcher(input);
            return matcher.matches();
        } else {
            return false;
        }
    }

    public void addCar(Scanner sc) {

        boolean doneID = false;
        boolean doneFrame = false;
        boolean doneEngine = false;
        boolean doneColor = false;

        Car temp = new Car();

        while (!doneID) {
            System.out.println("Enter car ID: ");
            String carID = sc.next();

            if (searchID(carID) != -1) {
                System.out.println("Car ID already exists");
            } else {
                temp.setCarID(carID);
                doneID = true;
            }
        }

        Brand b = bList.getUserChoice();
        temp.setBrand(b.getBrandID());

        while (!doneColor) {
            System.out.println("Enter color: ");
            String color = sc.next();
            if (color.isBlank()) {
                System.out.println("Color can not be blank");
            } else {
                temp.setColor(color);
                doneColor = true;
            }
        }

        while (!doneFrame) {
            System.out.println("Enter frame ID: ");
            String frameID = sc.next();
            if (!checkFormat(frameID, "F")) {
                System.out.println("Frame ID must be in the F00000 format");
            } else if (searchFrame(frameID) != -1) {
                System.out.println("Frame ID already exists");
            } else {
                temp.setFrameID(frameID);
                doneFrame = true;
            }
        }

        while (!doneEngine) {
            System.out.println("Enter engine ID: ");
            String engineID = sc.next();
            if (!checkFormat(engineID, "E")) {
                System.out.println("Engine ID must be in the E00000 format");
            } else if (searchEngine(engineID) != -1) {
                System.out.println("Engine ID already exists");
            } else {
                temp.setEngineID(engineID);
                doneEngine = true;
            }
        }

        this.add(temp);
        System.out.println("Car added successfully");
    }

    public void printBasedBrandName(String inp) {

        int n = this.size();
        int count = 0;
        for (int i = 0; i < n; i++) {
            Car c = this.get(i);

            String brandID = c.getBrand();

            int index = bList.searchID(brandID);

            if (index != -1) {
                Brand b = bList.get(index);

                if (b.getBrandName().contains(inp)) {
                    System.out.println(c.screenString());
                    count++;
                }

            }
        }
        if (count == 0) {
            System.out.println("No car is detected!");
        }
    }

    public boolean removeCar(String id) {

        int index = searchID(id);
        if (index == -1) {
            System.out.println("Not found");
            return false;
        } else {
            this.remove(index);
        }
        return true;
    }

    public boolean updateCar(String id, Scanner sc) {

        boolean doneFrame = false;
        boolean doneEngine = false;
        boolean doneColor = false;

        int index = searchID(id);
        if (index == -1) {
            return false;
        } else {
            Car temp = this.get(index);

            Brand b = bList.getUserChoice();
            temp.setBrand(b.getBrandID());

            while (!doneColor) {
                System.out.println("Enter color: ");
                String color = sc.next();
                if (color.isBlank()) {
                    System.out.println("Color can not be blank");
                } else {
                    temp.setColor(color);
                    doneColor = true;
                }
            }
            while (!doneFrame) {
                System.out.println("Enter frame ID: ");
                String frameID = sc.next();
                if (!checkFormat(frameID, "F")) {
                    System.out.println("Frame ID must be in the F00000 format");
                } else if (searchFrame(frameID) != -1) {
                    System.out.println("Frame ID already exists");
                } else {
                    temp.setFrameID(frameID);
                    doneFrame = true;
                }
            }

            while (!doneEngine) {
                System.out.println("Enter engine ID: ");
                String engineID = sc.next();
                if (!checkFormat(engineID, "E")) {
                    System.out.println("Engine ID must be in the E00000 format");
                } else if (searchEngine(engineID) != -1) {
                    System.out.println("Engine ID already exists");
                } else {
                    temp.setEngineID(engineID);
                    doneEngine = true;
                }
            }
            this.set(index, temp);
        }
        return true;
    }

    public void listCars() {

        int n = this.size();
        for (int i = 0; i < n; i++) {
            Car c = this.get(i);
            System.err.println(c.screenString());
        }
    }

}
