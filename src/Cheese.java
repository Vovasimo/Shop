public class Cheese extends Item {
    public Cheese(String name, Float price, String description) {
        super(name, price, description);
    }

    @Override
    public String toString() {
        return "cheese, " + name + ", " + price + ", " + description;
    }
}
