public abstract class Item {
    String name;
    Float price;
    String description;

    public Item(Float price, String description) {
        this.price = price;
        this.description = description;
    }

    public Item(String name, Float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
