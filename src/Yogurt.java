public class Yogurt extends Item {
    public Yogurt(String name, Float price, String description) {
        super(name, price, description);
    }

    @Override
    public String toString() {
        return "yogurt, " + name + ", " + price + ", " + description;
    }
}
