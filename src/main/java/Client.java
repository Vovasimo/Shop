public class Client {
    private String id;
    private String name;
    private String email;
    private Cart cart;

    public Client(String id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.cart = new Cart();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Cart getCart(){
        return cart;
    }
}
