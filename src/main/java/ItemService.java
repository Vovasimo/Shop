import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ItemService {
    public static ArrayList<Item> loadItems(String fileName) {
        ArrayList<Item> resultList = new ArrayList<Item>();
        try {
            String data = new String(Files.readAllBytes(Paths.get(fileName)));

            JSONArray jsonArray = new JSONArray(data);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String type = jsonObject.getString("type");
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                Float price = jsonObject.getFloat("price");
                String description = jsonObject.getString("description");


                resultList.add(new Item(id, name, type, price, description));
                }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return resultList;
    }
}
