import com.sun.tools.jconsole.JConsoleContext;

import java.io.Console;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

public class Main {

    public static List<Festmeny> festmenyek;

    public static enum Hibak {
        FileError,
        NotNumber,
        WrongNumber,
        Selled,
    }

    public static void main(String[] args) throws IOException {
        festmenyek = new ArrayList<>();

        festmenyek.add(new Festmeny("Hollókő", "Bálint Ferenc", "Expresszionizmus"));
        festmenyek.add(new Festmeny("Hómező", "Bálint Ferenc", "Konstruktivizmus"));
        festmenyek.add(new Festmeny("Reggel", "Bálint Ferenc", "Futurizmus"));


        Scanner sc = new Scanner(System.in);
        //Festmény hozzá  adás
        FestmenyAdd();

        //============================================================================
        //File beolvasás
        //============================================================================
        FileManagement fm = new FileManagement();
        try {
            festmenyek.addAll(fm.ReadFestmeny("festmenyek.csv"));
        } catch (IOException e) {
            HibaKezelo(Hibak.FileError);
        }
        //============================================================================
        //File beolvasás vége
        //============================================================================


        //============================================================================
        // Random indexű random licit
        //============================================================================
        int randomIndex = (int) (Math.random() * (festmenyek.size()));
        for (int i = 0; i < 20; i++) {
            int randomLicit = (int) (Math.random() * (100 - 10) + 1) + 10;
            festmenyek.get(randomIndex).Licit(randomLicit);

        }


        Licitalas();
        for (Festmeny festmeny : festmenyek) {
            if (festmeny.getLegmagasabbLicit() != 100) {
                festmeny.setElkelt(true);
            }
        }

        for (Festmeny festmeny : festmenyek) {
            System.out.println(festmeny.toString());
        }
        //============================================================================
        // Random indexű random licit vége
        //============================================================================
        legDragabb();
        if (tiznelTobbLicit()){
            System.out.println("Van olyan festmény amelyre tíznél több licit jött");
        } else {
            System.out.println("Nincs olyan festmény amire tíznél több licit jött volna");
        }


        int elNemKeltFestmenySzama = 0;
        for (Festmeny festmeny:festmenyek) {
            if (!festmeny.getElkelt()){
                elNemKeltFestmenySzama++;
            }
        }
        System.out.println("Összesen: " + elNemKeltFestmenySzama + "db festmény nem kelt el.");
        listaRendez();
        System.out.println("Rendezett lista");
        for (Festmeny f:festmenyek) {
            System.out.println(f.toString());
        }

        fm.WriteFestmenyek(festmenyek);

    }

    public static void FestmenyAdd() {
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

    }

    public static void Licitalas() {
        Scanner sc = new Scanner(System.in);
        int licitSzam = -1;

        boolean kezdes = true;
        boolean kilep = false;
        boolean ujrakezd = true;


        while (ujrakezd && !kilep) {

            ujrakezd = false;
            //Licit sorszám
            if (!kezdes) {
                HibaKezelo(Hibak.WrongNumber);
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
                HibaKezelo(Hibak.NotNumber);
            }

            licitSzam++;

            if ((licitSzam < 0) || ((licitSzam - 1) > festmenyek.size())) {
                ujrakezd = true;
            }

            if (festmenyek.get(licitSzam).getElkelt()) {
                HibaKezelo(Hibak.Selled);
                ujrakezd = true;
            }

            if (festmenyek.get(licitSzam).getLicitekSzama() > 0) {
                Duration duration = Duration.between(festmenyek.get(licitSzam).getLegutolsoLicitIdeje(), LocalDateTime.now());
                if (duration.toMinutes() >= 2 && !ujrakezd) {
                    festmenyek.get(licitSzam).setElkelt(true);
                    HibaKezelo(Hibak.Selled);
                    ujrakezd = true;
                }
            }
        }


        //Licit sorszám vége

        if (!ujrakezd && !kilep) {
            String licitMertek = "";
            sc.nextLine();
            System.out.println("Kérem adja meg a licit értékét: ");
            licitMertek = sc.nextLine();
            if (licitMertek.equals("")) {
                festmenyek.get(licitSzam).Licit();
            } else {
                try {
                    festmenyek.get(licitSzam).Licit(Integer.parseInt(licitMertek));
                    kilep = true;
                } catch (Exception e) {
                    HibaKezelo(Hibak.NotNumber);
                }
            }
        }

    }

    public static void HibaKezelo(Hibak hibaKod) {
        switch (hibaKod) {
            case Selled:
                System.out.println("A festmény már elkelt!");
                break;
            case FileError:
                System.out.println("Hibás file");
                break;
            case NotNumber:
                System.out.println("Nem számot adott meg!");
                System.exit(0);
            case WrongNumber:
                System.out.println("Rossz számot adott meg!");
                break;
        }
    }

    public static void legDragabb() {
        Festmeny legdragabb = festmenyek.get(0);
        for (Festmeny festmeny : festmenyek) {
            if (legdragabb.getLegmagasabbLicit() < festmeny.getLegmagasabbLicit()) {
                legdragabb = festmeny;
            }
        }
        System.out.println("A legdrágább festmény adatai: ");
        System.out.println(legdragabb.toString());
    }


    public static boolean tiznelTobbLicit() {
        for (Festmeny licitSzam:festmenyek) {
            if (licitSzam.getLicitekSzama() > 10) {
                return true;
            }
        }
        return false;
    }

    public static void listaRendez() {

        for (int i = 0; i < festmenyek.size(); i++) {  //eredeti: i=1
            Festmeny x = festmenyek.get(i);
            int j = i - 1;
            while(j>=0 && festmenyek.get(j).getLegmagasabbLicit()>x.getLegmagasabbLicit()) {
                festmenyek.set(j+1, festmenyek.get(j));
                j = j - 1;
            }
            festmenyek.set(j+1, x);
        }
    }

}