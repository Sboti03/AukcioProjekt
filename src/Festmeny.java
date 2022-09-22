import java.time.LocalDate;
import java.time.LocalDateTime;

public class Festmeny {
    private String cim;
    private String festo;
    private String stilus;
    private int licitekSzama;
    private int legmagasabbLicit;
    private LocalDateTime legutolsoLicitIdeje;
    private Boolean elkelt;

    public Festmeny(String cim, String festo, String stilus) {
        this.cim = cim;
        this.festo = festo;
        this.stilus = stilus;
        elkelt = false;
        legmagasabbLicit = 0;
        licitekSzama = 0;
    }

    public String getFesto() {
        return festo;
    }

    public String getCim() {
        return cim;
    }

    public String getStilus() {
        return stilus;
    }

    public int getLicitekSzama() {
        return licitekSzama;
    }

    public int getLegmagasabbLicit() {
        return legmagasabbLicit;
    }

    public LocalDateTime getLegutolsoLicitIdeje() {
        return legutolsoLicitIdeje;
    }

    public Boolean getElkelt() {
        return elkelt;
    }

    public void setElkelt(Boolean elkelt) {
        this.elkelt = elkelt;
    }



    public void Licit() {
        if (elkelt)
        {
            System.out.println("Hiba, a festmény már elkelt");
        } else {
            if (licitekSzama == 0) {
                legutolsoLicitIdeje = LocalDateTime.now();
                legmagasabbLicit = 100;
                licitekSzama++;
            } else {
                Licit(10);
            }
        }
    }

    public void Licit(int mertek) {
        if (mertek > 100 && mertek < 10) {
            System.out.println("Hibás érték");
        }else {
            if (elkelt)
            {
                System.out.println("Hiba, a festmény már elkelt");
            } else {
                if (licitekSzama == 0) {
                    legutolsoLicitIdeje = LocalDateTime.now();
                    legmagasabbLicit = 100;
                    licitekSzama++;
                } else {
                    legutolsoLicitIdeje = LocalDateTime.now();
                    legmagasabbLicit = (legmagasabbLicit * (mertek + 100)) / 100;
                    licitekSzama++;
                    legmagasabbLicit = (int)Math.round((double) legmagasabbLicit/100)*100;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append(festo).append(": ").append(cim).append(" (").append(stilus).append(")\n");
        if (elkelt){
            strb.append("elkelt\n");
        }
        strb.append(legmagasabbLicit).append("$ - ").append(legutolsoLicitIdeje.toString());
        strb.append(" (összesen: ").append(licitekSzama).append("db");
        return  strb.toString();
    }
}
