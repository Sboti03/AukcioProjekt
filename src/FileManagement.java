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
}
