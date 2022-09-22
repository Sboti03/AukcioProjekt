import com.sun.tools.jconsole.JConsoleContext;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Festmeny> festmenyek = new ArrayList<>();
        festmenyek.add(new Festmeny("Hollókő", "Bálint Ferenc", "Expresszionizmus"));
        festmenyek.add(new Festmeny("Hómező", "Bálint Ferenc", "Konstruktivizmus"));
        festmenyek.add(new Festmeny("Reggel", "Bálint Ferenc", "Futurizmus"));


        Scanner sc = new Scanner(System.in);
        System.out.print("Kérem adjon meg egy darabszámot: ");
        int darabSzam = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < darabSzam; i++) {
            System.out.println("Kérem adja meg a festmény címét:");
            String cim = sc.nextLine();

            System.out.println("Kérem adja meg a festő nevét:");
            String festo = sc.nextLine();

            System.out.println("Kérem adja meg a stílist:");
            String stilus = sc.nextLine();

            festmenyek.add(new Festmeny(cim, festo, stilus));
        }

        FileManagement fm = new FileManagement();
        try {
            festmenyek.addAll(fm.ReadFestmeny("festmenyek.csv"));
        } catch (IOException e) {
            System.out.println("Hiba a fálj beolvasása során");
        }


        int randomIndex = (int)(Math.random() * (festmenyek.size()));
        for (int i = 0; i < 20; i++) {
            int randomLicit = (int)(Math.random() * (100 - 10) + 1) + 10;
            festmenyek.get(randomIndex).Licit(randomLicit);
            System.out.println("Licit értéke " +
                    randomLicit + " új érték: " + festmenyek.get(randomIndex).getLegmagasabbLicit());
        }


        int licitSzam = -1;
        boolean kezdes = true;
        boolean kilep = false;

        while (licitSzam < 0 || (licitSzam - 1) > festmenyek.size() && !kilep) {

            if (!kezdes) {
                System.out.println("Hibás értéket adott meg");
            } else {
                kezdes = true;
            }

            System.out.print("Kérem adja meg a licit sorszámát: ");
            try {
                licitSzam = sc.nextInt();
                if (licitSzam == 0) {
                    kilep = true;
                }
            } catch (Exception e) {
                System.out.println("Nem számot adott meg");
                System.exit(0);
            }
        }

        if (!kilep) {

        }



    }

}