import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path filepath = Paths.get("src/Data.txt");
        try {
            List<String> lines = Files.readAllLines(filepath);
            List<Item> items = new ArrayList<>();
            List<String[]> data = new ArrayList<>();
            for (String line : lines) {
                data.add(line.split(" ",3));
            }
            data.forEach(row -> items.add(new Item(String.join(",",row))));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}