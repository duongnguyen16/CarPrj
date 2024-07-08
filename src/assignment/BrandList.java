package assignment;

import java.io.*;
import java.util.*;

public class BrandList extends ArrayList<Brand> {
    public BrandList() {
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
                    String soundBrand = parts[2].substring(0, parts[2].indexOf(":"));
                    double price = Double.parseDouble(parts[2].substring(parts[2].indexOf(":") + 2));
                    Brand brand = new Brand(parts[0], parts[1], soundBrand, price);
                    this.add(brand);
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

            for (Brand brand : this) {
                pw.println(brand.getBrandID() + ", " + brand.getBrandName() + ", " + brand.getSoundBrand() + ":"
                        + brand.getPrice() + "\n");
            }

            pw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int searchID(String bID) {
        int n = this.size();

        for (int i = 0; i < n; i++) {
            // System.out.println("DEBUG> searchID: " + this.get(i).getBrandID() + " - " +
            // bID);
            if (this.get(i).getBrandID().equals(bID)) {
                return i;
            }
        }
        return -1;
    }

    public Brand getUserChoice() {
        Menu<Brand> menu = new Menu<Brand>();
        Scanner sc = new Scanner(System.in);
        return menu.ref_getChoice(this, sc);
    }

    public void addBrand(Scanner sc) {

        boolean doneID = false;
        boolean doneName = false;
        boolean doneSound = false;
        boolean donePrice = false;

        /*
         * • Brand ID can not be duplicated.
         * • Brand name can not be blank.
         * • Sound manufacturer can not be blank.
         * • Price must be a positive real number.
         * 
         */

        Brand temp = new Brand();

        System.out.println("Enter brand ID: ");
        temp.setBrandID(sc.nextLine());
        if (temp.getBrandID().equals("")) {
            System.out.println("Please re-enter the brand ID: ");
            temp.setBrandID(sc.nextLine());
        }

        System.out.println("Enter brand name: ");
        temp.setBrandName(sc.nextLine());
        if (temp.getBrandName().equals("")) {
            System.out.println("Please re-enter the brand name: ");
            temp.setBrandName(sc.nextLine());
        }

        System.out.println("Enter sound brand: ");
        temp.setSoundBrand(sc.nextLine());
        if (temp.getSoundBrand().equals("")) {
            System.out.println("Please re-enter the sound brand: ");
            temp.setSoundBrand(sc.nextLine());
        }

        System.out.println("Enter price: ");
        temp.setPrice(sc.nextDouble());
        if (temp.getPrice() <= 0) {
            System.out.println("Please re-enter the price: ");
            temp.setPrice(sc.nextDouble());
        }

        this.add(temp);

    }

    public void updateBrand(String bID, Scanner sc) {

        int index = searchID(bID);
        if (index == -1) {
            System.out.println("Brand not found");
        } else {
            Brand temp = this.get(index);
            System.out.println("Enter new brand ID: ");
            temp.setBrandID(sc.next());
            if (temp.getBrandID().equals("")) {
                System.out.println("Please re-enter the new brand ID: ");
                temp.setBrandID(sc.next());
            }
            System.out.println("Enter new brand name: ");
            temp.setBrandName(sc.next());
            if (temp.getBrandName().equals("")) {
                System.out.println("Please re-enter the new brand name: ");
                temp.setBrandName(sc.next());
            }
            System.out.println("Enter new sound brand: ");
            temp.setSoundBrand(sc.next());
            if (temp.getSoundBrand().equals("")) {
                System.out.println("Please re-enter the new sound brand: ");
                temp.setSoundBrand(sc.next());
            }
            System.out.println("Enter new price: ");
            temp.setPrice(sc.nextDouble());
            if (temp.getPrice() == 0) {
                System.out.println("Please re-enter the new price: ");
                temp.setPrice(sc.nextDouble());
            }
        }
    }

    public void listBrand() {
        // System.out.println("DEBUG> List of brand: ");
        int n = this.size();
        for (int i = 0; i < n; i++) {
            System.out.println(this.get(i));
        }
    }
}
