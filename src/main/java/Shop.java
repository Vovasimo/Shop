import java.util.ArrayList;

public class Shop {
    private ArrayList<Item> list;
    private ArrayList<Client> clients;
    String dataFileName;
    String clientFileName;

    public Shop(String dataFileName, String clientFileName) {
        this.list = ItemService.loadItems(dataFileName);
        this.clients = ClientService.loadClients(clientFileName);
        this.dataFileName = dataFileName;
        this.clientFileName = clientFileName;
    }

    public void show(){
        for (Item item : list) {
            System.out.println(item.toString());
        }
    }

    public String showInString(){
        StringBuilder result = new StringBuilder();
        for (Item item : list) {
            result.append(item.toString()).append("\n");
        }
        return result.toString();
    }

    public void update(){
        this.list = ItemService.loadItems(dataFileName);
        this.clients = ClientService.loadClients(clientFileName);
        System.out.println("\nList was UPDATED!\n");
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

}
