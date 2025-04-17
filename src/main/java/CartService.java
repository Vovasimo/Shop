public class CartService {
    public static void addToCart(Cart cart, Item item) {
        cart.getList().add(item);
    }

    public static void removeFromCart(Cart cart, Item item) {
        cart.getList().remove(item);
    }

    public static void clearCart(Cart cart) {
        cart.getList().clear();
    }

    public static Float calculateTotal(Cart cart) {
        return cart.sumOfItems();
    }
}
