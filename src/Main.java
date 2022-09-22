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
        System.out.print("Kérem adjon meg egy darabszámot");
        int darabSzam = sc.nextInt();
        for (int i = 0; i < darabSzam; i++) {
            System.out.println("Kérem adja meg a festmény címét: ");
            String cim = sc.nextLine();
            System.out.println("Kérem adja meg a festő nevét:");
            String festo = sc.nextLine();
            System.out.println("Kérem adja meg a stílist:");
            String stilus = sc.nextLine();
            festmenyek.add(new Festmeny(cim,festo,stilus));
        }

        FileManagement fm = new FileManagement();
        try {
            festmenyek.addAll(fm.ReadFestmeny("festmenyek.csv"));
        } catch (IOException e) {
            System.out.println("Hiba a fálj beolvasása során");
        }
    }
}