import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataController {

    public static ArrayList<Item> readFromFile(String fileName) {
        ArrayList<Item> items = new ArrayList<>();
        try {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get("src/" + fileName)));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    System.out.println("Incorrect line: " + line);
                    continue;
                }
                String type = parts[0].trim().toLowerCase();
                String name = parts[1].trim();
                Float price = Float.parseFloat(parts[2].trim());
                String description = parts[3].trim();

                switch (type) {
                    case "cheese":
                        items.add(new Cheese(name, price, description));
                        break;
                    case "milk":
                        items.add(new Milk(name, price, description));
                        break;
                    case "yogurt":
                        items.add(new Yogurt(name, price, description));
                        break;
                    default:
                        System.out.println("Unknown item: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error(readFromFile): " + e.getMessage());
        }
        return items;
    }

    public static void addToFile(String fileName, Item item) {
        try {
            FileWriter fileWriter = new FileWriter("src/"+fileName, true);
            fileWriter.write("\n" + item.toString());
            fileWriter.close();
            System.out.println("Item added to file: " + item);
        } catch (IOException e) {
            System.out.println("Error(addToFile): " + e.getMessage());
        }
    }

    public static void removeFromFile(String fileName, Item item) {
        try {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get("src/" + fileName)));
            String itemString = item.toString();
            ArrayList<String> updatedLines = new ArrayList<String>();

            for (String line : lines) {
                if (!line.trim().equals(itemString)) {
                    updatedLines.add(line);
                }
            }

            FileWriter fileWriter = new FileWriter("src/" + fileName, false);
            for (int i = 0; i < updatedLines.size(); i++) {
                fileWriter.write(updatedLines.get(i));
                if (i < updatedLines.size() - 1) {
                    fileWriter.write("\n");
                }
            }
            fileWriter.close();

            System.out.println("Item removed from file: " + item);
        } catch (IOException e) {
            System.err.println("Error(removeFromFile): " + e.getMessage());
        }
    }
}
