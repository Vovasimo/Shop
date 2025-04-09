import java.util.Objects;

public abstract class Item {
    private String id;
    private String name;
    private Float price;
    private String description;

    public Item(String id, String name, Float price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+":\n" +"id: "+ getId() + ", \n" +"name: "+ getName() + ", \n" +"price: "+ getPrice() + ", \n" +"description: "+ getDescription() + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(price, item.price) &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, description);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
}
