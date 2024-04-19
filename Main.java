import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static final String base = ".//Games//";

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        List<File> dirList = Arrays.asList(
                new File(base),
                new File(base + "temp"),
                new File(base + "src"),
                new File(base + "res"),
                new File(base + "savegames"),
                new File(base + "src//main"),
                new File(base + "src//test"),
                new File(base + "res//drawables"),
                new File(base + "res//vectors"),
                new File(base + "res//icons")
        );
        List<File> fileList = Arrays.asList(
                new File(base + "src//main//Main.java"),
                new File(base + "src//main//Utils.java"),
                new File(base + "temp//temp.txt")
        );
        dirList.stream().forEach(dir -> {
            if (dir.mkdir()) sb.append(dir + " - каталог создан\n");
            else sb.append(dir + " - каталог не создан\n");
        });

        fileList.stream().forEach(file -> {
            try {
                if (file.createNewFile()) sb.append(file + " - файл создан\n");
                else sb.append(file + " - файл не создан\n");
            } catch (IOException ex) {
                sb.append(ex.getMessage() + '\n');
            }
        });
        try (FileWriter log = new FileWriter(base + "temp//temp.txt",false)) {
            log.write(sb.toString());
            log.flush();
        } catch (IOException ex) {
            sb.append(ex.getMessage() + '\n');
        }
        try (BufferedReader br = new BufferedReader(new FileReader(base + "temp//temp.txt"))) {
            String s;
            while ((s = br.readLine()) != null) System.out.println(s);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
