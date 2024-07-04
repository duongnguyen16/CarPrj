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
            // Open file in text format for reading line-by-line;
            try {
                Scanner s = new Scanner(f);
                while (s.hasNextLine()) {
                    String line = s.nextLine();
                    String[] parts = line.split(",");
                    // B7-2018, BMW 730Li (2018), Harman Kardon: 3.749
                    // follow this: ID, brand name, sound brand: price
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
                pw.print(brand.getBrandID() + ", " + brand.getBrandName() + ", " + brand.getSoundBrand() + ":"
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
            if (this.get(i).getBrandID() == bID) {
                return i;
            }
        }
        return -1;
    }

    public Brand getUserChoice() {
        Menu menu = new Menu();
        ArrayList<String> options = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        return menu.ref_getChoice(this, sc);
    }

    public void addBrand(Scanner sc) {
        Brand temp = new Brand();

        // get user input
        System.out.println("Enter brand ID: ");
        temp.setBrandID(sc.next());
        System.out.println("Enter brand name: ");
        temp.setBrandName(sc.next());
        System.out.println("Enter sound brand: ");
        temp.setSoundBrand(sc.next());
        System.out.println("Enter price: ");
        temp.setPrice(sc.nextDouble());

        this.add(temp);

    }

    public void updateBrand(String bID) {
        Scanner sc = new Scanner(System.in);

        int index = searchID(bID);
        if (index == -1) {
            System.out.println("Brand not found");
        } else {
            Brand temp = this.get(index);
            System.out.println("Enter new brand ID: ");
            temp.setBrandID(sc.next());
            System.out.println("Enter new brand name: ");
            temp.setBrandName(sc.next());
            System.out.println("Enter new sound brand: ");
            temp.setSoundBrand(sc.next());
            System.out.println("Enter new price: ");
            temp.setPrice(sc.nextDouble());
        }
        sc.close();
    }

    public void listBrand() {
        System.out.println("DEBUG> List of brand: ");
        int n = this.size();
        for (int i = 0; i < n; i++) {
            System.out.println(this.get(i));
        }
    }
}
