import java.util.ArrayList;

public class Shop {
    private ArrayList<Item> list;
    String dataFileName;

    public Shop(String dataFileName) {
        this.list = ItemService.loadItems(dataFileName);
        this.dataFileName = dataFileName;
    }

    public void show(){
        for (Item item : list) {
            System.out.println(item.toString());
        }
    }

    public void update(){
        this.list = ItemService.loadItems(dataFileName);
        System.out.println("\nFile was UPDATED!\n");
    }
}
