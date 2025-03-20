import java.util.ArrayList;

public class Cart {
    private ArrayList<Item> list;

    public Cart() {
        this.list = new ArrayList<Item>();
    }

    public Float sumOfItems() {
        Float sum = 0F;
        for (Item item : list) {
            sum += item.getPrice();
        }
        return sum;
    }

    public ArrayList<Item> show() {
        for (Item item : list) {
            System.out.println(item.toString());
        }
    }

    public ArrayList<Item> getList() {
        return list;
    }

    public void setList(ArrayList<Item> list) {
        this.list = list;
    }
}
