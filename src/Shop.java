import java.util.ArrayList;

public class Shop {
    ArrayList<Item> list;
    String dataFileName;

    public Shop(String dataFileName) {
        this.list = DataController.readFromFile(dataFileName);
        this.dataFileName = dataFileName;
    }
}
