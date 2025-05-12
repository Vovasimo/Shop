import java.util.ArrayList;

public class Shop {
    private ArrayList<Item> list;
    private ArrayList<Client> clients;
    public CartService cartService;
    public final String dataFileName;
    public final String clientFileName;

    public Shop(String dataFileName, String clientFileName) {
        this.list = ItemService.loadItems(dataFileName);
        this.clients = ClientService.loadClients(clientFileName);
        this.cartService = new CartService();
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

    public ArrayList<Item> getList() {
        return list;
    }
    public ArrayList<Client> getClients() {
        return clients;
    }

}
