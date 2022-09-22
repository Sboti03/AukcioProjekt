import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagement {
    public List<Festmeny> ReadFestmeny(String path) throws IOException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<Festmeny> festmenyek = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] elements = line.split(";");
            festmenyek.add(new Festmeny(elements[1], elements[0], elements[2]));
        }

        return festmenyek;
    }

    public void WriteFestmenyek(List<Festmeny> lista) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("festmenyek_rendezett.csv"));
        for (Festmeny f:lista) {
            bw.write(f.getFesto()+";"+f.getCim()+";"+f.getStilus());
        }
        bw.close();

    }
}
