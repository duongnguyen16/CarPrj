package assignment;

import java.util.*;

public class CarManager {

    public static ArrayList<Car> sortByBrand(ArrayList<Car> cars) {
        Collections.sort(cars, new Comparator<Car>() {
            @Override
            public int compare(Car c1, Car c2) {
                return c1.compareTo(c2);
            }
        });
        return cars;
    }

    public static void main(String[] args) {

        BrandList bList = new BrandList();
        CarList cList = new CarList(bList);

        bList.loadFromFile("Brands.txt");
        cList.loadFromFile("Cars.txt");

        cList.listCars();
        bList.listBrand();

        ArrayList<String> ops = new ArrayList<>(Arrays.asList("List all brand", "Add a new brand", "Search a brand",
                "Update a brand", "Save brands to file", "List all cars in ascending order of brand names",
                "List cars based on a part of an input brand",
                "Add a car", "Remove a car based on its ID", "Update a car based on its ID",
                "Save cars to file",
                "Exit"));
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        Menu<String> menu = new Menu<>();
        do {
            choice = menu.int_getChoice(ops, sc);
            switch (choice) {
                case 1:
                    bList.listBrand();
                    break;
                case 2:
                    bList.addBrand(sc);
                    break;
                case 3:
                    System.out.println("Enter brand ID to search: ");
                    String brandID = sc.next();
                    int result = bList.searchID(brandID);
                    if (result == -1) {
                        System.out.println("Brand not found");
                    } else {
                        System.out.println(bList.get(result).toString());
                    }
                    break;
                case 4:
                    System.out.println("Enter brand ID to update: ");
                    String bID = sc.next();
                    bList.updateBrand(bID, sc);
                    break;
                case 5:
                    bList.saveToFile("Brands.txt");
                    break;
                case 6:
                    ArrayList<Car> sortedList = sortByBrand(cList);
                    for (Car c : sortedList) {
                        System.out.println(c.screenString());
                    }
                    break;
                case 7:
                    System.out.println("Enter a part of brand name: ");
                    String inp = sc.next();
                    cList.printBasedBrandName(inp);
                    break;
                case 8:
                    cList.addCar(sc);
                    break;
                case 9:
                    System.out.println("Enter car ID to remove: ");
                    String carID = sc.next();
                    if (cList.removeCar(carID)) {
                        System.out.println("Car removed successfully");
                    }
                    break;
                case 10:
                    System.out.println("Enter car ID to update: ");
                    String cID = sc.next();
                    if (cList.updateCar(cID, sc)) {
                        System.out.println("Car updated successfully");
                    } else {
                        System.out.println("Car not found");
                    }
                    break;
                case 11:
                    cList.saveToFile("Cars.txt");
                    break;
                case 12:
                    break;
                default:
                    break;
            }

            System.out.println("Press anykey to continue...");

            try {
                System.in.read();
            } catch (Exception e) {
            }

        } while (choice > 0 && choice <= ops.size());
    }
}
