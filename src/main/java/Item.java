import org.json.JSONObject;

import java.util.Objects;

public class Item {
    private String id;
    private String name;
    private String type;
    private Float price;
    private String description;

    public Item(String id, String name, String type, Float price, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public Item(JSONObject object) {
        this.id = object.get("id").toString();
        this.name = object.get("name").toString();
        this.type = object.get("type").toString();
        this.price = object.getFloat("price");
        this.description = object.get("description").toString();
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(type, item.type) &&
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

    public String getType() { return type; }

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
