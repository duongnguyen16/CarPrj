package assignment;

import java.util.*;

public class Menu<E> {
    public int int_getChoice(ArrayList<E> Options, Scanner sc) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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

    public E ref_getChoice(ArrayList<E> Options, Scanner sc) {
        System.out.println(">DEBUG: ref_getChoice(ArrayList<E> Options)");

        int response;
        int n = Options.size();
        do {
            response = int_getChoice((ArrayList<E>) Options, sc);
        } while (response < 0 || response > n);
        return Options.get(response - 1);
    }
}
