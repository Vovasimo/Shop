public class Milk extends Item {
    public Milk(String name, Float price, String description) {
        super(name, price, description);
    }

    @Override
    public String toString() {
        return "milk, " + name + ", " + price + ", " + description;
    }
}
