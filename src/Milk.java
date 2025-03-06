public class Milk extends Item{
    public Milk(String name, Float price, String description) {
        super(name, price, description);
    }

    public Milk(Float price, String description) {
        super("Milk", price, description);
    }
}
