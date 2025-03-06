public class Cheese extends Item {
    public Cheese(String name, Float price, String description) {
        super(name, price, description);
    }

    public Cheese(Float price, String description) {
        super("Cheese", price, description);
    }
}
