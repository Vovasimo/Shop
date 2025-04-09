import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ClientService {
    public static ArrayList<Client> loadClients(String fileName) {
        ArrayList<Client> resultList = new ArrayList<Client>();
        try {
            String data = new String(Files.readAllBytes(Paths.get(fileName)));

            JSONArray jsonArray = new JSONArray(data);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String email = jsonObject.getString("email");
                String name = jsonObject.getString("name");

                resultList.add(new Client(id, email, name));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return resultList;
    }
}
