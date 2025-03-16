public class ShopController {
    private final Shop shop;

    public ShopController(Shop shop) {
        this.shop = shop;
    }

    public void showList() {
        System.out.println("Shop List {");
        for (int i = 1; i != shop.list.size() + 1; i++) {
            System.out.println(i + ". " + shop.list.get(i - 1).toString());
        }
        System.out.println("}");
    }

    public void addToShop(Item item) {
        shop.list.add(item);
        DataController.addToFile(shop.dataFileName, item);
    }

    public void addToShop(String type, String name, Float price, String description) {
        switch (type) {
            case "cheese":
                addToShop(new Cheese(name, price, description));
                break;
            case "milk":
                addToShop(new Milk(name, price, description));
                break;
            case "yogurt":
                addToShop(new Yogurt(name, price, description));
                break;
            default:
                System.out.println("Incorrect type: " + type);
        }

    }

    public void removeFromShop(Item item) {
        shop.list.removeIf(shopItem -> shopItem.equals(item));
        DataController.removeFromFile(shop.dataFileName, item);
    }

    public void removeFromShop(String type, String name, Float price, String description) {
        switch (type) {
            case "cheese":
                removeFromShop(new Cheese(name, price, description));
                break;
            case "milk":
                removeFromShop(new Milk(name, price, description));
                break;
            case "yogurt":
                removeFromShop(new Yogurt(name, price, description));
                break;
            default:
                System.out.println("Incorrect type: " + type);
        }

    }

}
