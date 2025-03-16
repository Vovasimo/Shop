import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return Objects.equals(name, item.name) &&
                Objects.equals(price, item.price) &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, description);
    }
}
