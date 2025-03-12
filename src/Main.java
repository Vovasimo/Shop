public class Main {
    public static void main(String[] args) {
        System.out.println("WELCOME!");

        String fileName = "Data.txt";
        Shop shop = new Shop(fileName);
        ShopController controller = new ShopController(shop);

        System.out.println("\n");
        controller.showList();
        System.out.println("\n\n");

        Cheese cheese = new Cheese("Mozzarella", 4.99F, "Italian cheese for pizza");
        System.out.println("Add Cheese and Milk to List");
        controller.addToShop(cheese);
        controller.addToShop("milk", "Soy Milk", 3.49F, "Lactose-free alternative");

        System.out.println("\n");
        controller.showList();
        System.out.println("\n");

        System.out.println("Remove Cheese and Milk from List");
        controller.removeFromShop(cheese);
        controller.removeFromShop("milk", "Soy Milk", 3.49F, "Lactose-free alternative");

        System.out.println("\n");
        controller.showList();
    }
}