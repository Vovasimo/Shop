import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManagerService {

    public static JSONObject findAndReturn(String key, String value, String fileName) {
        try {
            String bytes = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONArray jsonArray = new JSONArray(bytes);

            for(int i=0; i < jsonArray.length();i++){
                if(jsonArray.getJSONObject(i).get(key).equals(value)){
                    System.out.println("Object found");
                    return jsonArray.getJSONObject(i);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static JSONObject findAndRemoveFromList(String key, String value, String fileName) {
        try {
            String bytes = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONArray jsonArray = new JSONArray(bytes);

            for(int i=0; i < jsonArray.length();i++){
                if(jsonArray.getJSONObject(i).get(key).equals(value)){
                    System.out.println("Object found");
                    JSONObject res = jsonArray.getJSONObject(i);
                    jsonArray.remove(i);
                    return res;
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

                    //ITEMS


    public static void addItemToJSON(Item item, String fileName) {
        addItemToJSON(item.getClass().getSimpleName().toLowerCase(), item.getId(), item.getName(), item.getPrice(), item.getDescription(), fileName);
    }

    public static void addItemToJSON(String type, String id, String name, Float price, String description, String fileName) {
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

    public static void removeItemFromJSON(Item item, String fileName) {
        removeItemFromJSON(item.getClass().getSimpleName().toLowerCase(), item.getId(), fileName);
    }

    public static void removeItemFromJSON(String type, String id, String fileName) {
        boolean removed = false;
        try {
            String bytes = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONArray jsonArray = new JSONArray(bytes);


            for(int i=0; i < jsonArray.length();i++){
                if(jsonArray.getJSONObject(i).get("type").equals(type) && jsonArray.getJSONObject(i).get("id").equals(id)){
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


                    //CLIENTS


    public static void addClientToJSON(String id, String email, String password, String name, String fileName) {
        JSONObject object = new JSONObject();

        object.put("id", id);
        object.put("email", email);
        object.put("password", password.hashCode());
        object.put("name", name);

        try {
            String bytes = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONArray jsonArray = new JSONArray(bytes);
            boolean exists = false;

            for(int i=0; i < jsonArray.length();i++){
                if(jsonArray.getJSONObject(i).get("email").equals(email) && jsonArray.getJSONObject(i).get("id").equals(id)){
                    exists = true;
                    break;
                }
            }

            if(!exists){
                jsonArray.put(object);
                Files.write(Paths.get(fileName), jsonArray.toString(4).getBytes());
                System.out.println("Client added to file");
            }else{
                System.out.println("Client already exists");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    public static void removeClientFromJSON(Client client, String fileName) {
        removeClientFromJSON(client.getId(), client.getEmail(), client.getName(), fileName);
    }

    public static void removeClientFromJSON(String id, String email, String name, String fileName) {
        boolean removed = false;
        try {
            String bytes = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONArray jsonArray = new JSONArray(bytes);


            for(int i=0; i < jsonArray.length();i++){
                if(jsonArray.getJSONObject(i).get("id").equals(id) && jsonArray.getJSONObject(i).get("email").equals(email)){
                    jsonArray.remove(i);
                    removed = true;
                }
            }
            if(removed){
                Files.write(Paths.get(fileName), jsonArray.toString(4).getBytes());
                System.out.println("Client removed from file");
            }else{
                System.out.println("Client not in list :(");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
