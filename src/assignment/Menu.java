package assignment;

import java.util.*;

public class Menu {
    public int int_getChoice(ArrayList<String> Options, Scanner sc) {
        System.out.println(">DEBUG: int_getChoice(ArrayList<String> Options)");
        int response;
        int n = Options.size();

        for (int i = 0; i < n; i++) {
            System.out.println(i + 1 + "> " + Options.get(i));
        }

        System.out.print("Choose an option: ");
        response = sc.nextInt();
        return response;
    }

    public <E> E ref_getChoice(ArrayList<E> Options, Scanner sc) {
        System.out.println(">DEBUG: ref_getChoice(ArrayList<E> Options)");

        int response;
        int n = Options.size();
        do {
            response = int_getChoice((ArrayList<String>) Options, sc);
        } while (response < 0 || response > n);
        return Options.get(response - 1);
    }
}
