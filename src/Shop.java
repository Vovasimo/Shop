import java.util.ArrayList;

public class Shop {
    ArrayList<Item> list;

    public void show(){
        System.out.println("List of items: \n");
        for(int i = 0;i!=list.size();i++){
            System.out.println(list.get(i).toString());
        }
    }
}
