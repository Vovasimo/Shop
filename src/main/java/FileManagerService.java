import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManagerService {

    public void addObjectToFile(Item item, String fileName) {
        addObjectToFile(item.getClass().getSimpleName().toLowerCase(), item.getId(), item.getName(), item.getPrice(), item.getDescription(), fileName);
    }

    public void addObjectToFile(String type, Integer id, String name, Float price, String description, String fileName) {
        JSONObject object = new JSONObject();

        object.put("type", type);
        object.put("id", id);
        object.put("name", name);
        object.put("price", price);
        object.put("description", description);

        try {
            String bytes = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONArray jsonArray = new JSONArray(bytes);

            jsonArray.put(object);

            Files.write(Paths.get(fileName), jsonArray.toString(4).getBytes());
            System.out.println("Item added to file: " + object.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void removeObjectFromFile(Item item, String fileName) {
        removeObjectFromFile(item.getClass().getSimpleName().toLowerCase(), item.getId(), item.getName(), item.getPrice(), item.getDescription(), fileName);
    }

    public void removeObjectFromFile(String type, Integer id, String name, Float price, String description, String fileName) {
        JSONObject object = new JSONObject();

        object.put("type", type);
        object.put("id", id);
        object.put("name", name);
        object.put("price", price);
        object.put("description", description);

        try {
            String bytes = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONArray jsonArray = new JSONArray(bytes);

            jsonArray.put(object);
            for(int i=0; i!= jsonArray.length();i++){
                if(jsonArray.get(i).equals(object)){
                    jsonArray.remove(i);
                }
            }

            Files.write(Paths.get(fileName), jsonArray.toString(4).getBytes());
            System.out.println("Item removed from file: " + object.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
