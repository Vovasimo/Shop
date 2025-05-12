import spark.Request;
import spark.Session;

import java.util.ArrayList;

public class CartService {
    public static Cart getCart(Request req) {
        Session session = req.session(true);
        Cart cart = session.attribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.attribute("cart", cart);
        }
        return cart;
    }

    public static boolean find(Request req, Item item) {
        ArrayList<Item> array = getCart(req).getList();
        for (Item value : array) {
            if (value.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Item> getCartItems(Request req) {
        return getCart(req).getList();
    }

    public void addToCart(Request req, Item item) {
        Cart cart = getCart(req);
        if (!CartService.find(req, item)) {
            getCart(req).getList().add(item);
        }
    }

    public void removeFromCart(Request req, Item item) {
        getCart(req).getList().remove(item);
    }

    public void clearCart(Request req) {
        getCart(req).getList().clear();
    }

    public Float calculateTotal(Request req, Cart cart) {
        return getCart(req).sumOfItems();
    }
}
