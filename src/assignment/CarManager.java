package assignment;

import java.util.*;

public class CarManager {
    public static void main(String[] args) {

        CarList cList = new CarList();
        BrandList bList = new BrandList();

        // load data from file
        bList.loadFromFile("Brands.txt");
        cList.loadFromFile("Cars.txt");

        // show all element of the list
        cList.listCars();
        bList.listBrand();

        // Create ArrayList ops of strings containing options of the program;
        /*
         * The manager of the showroom needs a Java console application in which
         * operations must be supported:
         * 1- List all brands
         * 2- Add a new brand
         * 3- Search a brand based on its ID
         * 4- Update a brand
         * 5- Save brands to the file, named brands.txt
         * 6- List all cars in ascending order of brand names
         * 7- List cars based on a part of an input brand name
         * 8- Add a car
         * 9- Remove a car based on its ID
         * 10- Update a car based on its ID
         * 11- Save cars to file, named cars.txt
         * 
         */
        ArrayList<String> ops = new ArrayList<>(Arrays.asList("List all brand", "Add a new brand", "Search a brand",
                "Update a brand", "Save brands to file", "List all cars", "List cars based on a part of an input brand",
                "Add a car", "Remove a car", "Update a car", "Save cars to file", "Exit"));
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
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
                    String brandID = sc.nextLine();
                    bList.searchID(brandID);
                    break;
                case 4:
                    System.out.println("Enter brand ID to update: ");
                    String bID = sc.nextLine();
                    bList.updateBrand(bID);
                    break;
                case 5:
                    bList.saveToFile("Brands.txt");
                    break;
                case 6:
                    cList.listCars();
                    break;
                case 7:
                    System.out.println("Enter a part of brand name: ");
                    String inp = sc.nextLine();
                    cList.printBasedBrandName(inp);
                    break;
                case 8:
                    cList.addCar();
                    break;

                case 9:
                    System.out.println("Enter car ID to remove: ");
                    String carID = sc.nextLine();
                    cList.removeCar(carID);
                    break;

                case 10:
                    System.out.println("Enter car ID to update: ");
                    String cID = sc.nextLine();
                    cList.updateCar(cID);
                    break;
                case 11:
                    cList.saveToFile("Cars.txt");
                    break;
                case 12:
                    break;
                default:
                    break;
            }
        } while (choice > 0 && choice <= ops.size());
    }
}
