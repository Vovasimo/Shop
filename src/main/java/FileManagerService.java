import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManagerService {

    public void addObjectToJSON(Item item, String fileName) {
        addObjectToJSON(item.getClass().getSimpleName().toLowerCase(), item.getId(), item.getName(), item.getPrice(), item.getDescription(), fileName);
    }

    public void addObjectToJSON(String type, Integer id, String name, Float price, String description, String fileName) {
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

    public void removeObjectFromJSON(Item item, String fileName) {
        removeObjectFromJSON(item.getClass().getSimpleName().toLowerCase(), item.getId(), fileName);
    }

    public void removeObjectFromJSON(String type, Integer id, String fileName) {
        boolean removed = false;
        try {
            String bytes = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONArray jsonArray = new JSONArray(bytes);


            for(int i=0; i< jsonArray.length();i++){
                if(jsonArray.getJSONObject(i).get("type").equals(type) || jsonArray.getJSONObject(i).get("id").equals(id)){
                    jsonArray.remove(i);
                    removed = true;
                }
            }
            if(removed){
                Files.write(Paths.get(fileName), jsonArray.toString(4).getBytes());
                System.out.println("Item removed from file");
            }else{
                System.out.println("Item not in list :(");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
