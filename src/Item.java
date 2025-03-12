public abstract class Item {
    String name;
    Float price;
    String description;

    public Item(String name, Float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item, " + name + ", " + price + ", " + description;
    }
}
