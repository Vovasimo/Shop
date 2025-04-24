import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class User implements Runnable{
    private Socket socket;
    private Shop shop;
    private Cart cart;
    private Client client;

    public User(Socket socket, Shop shop, Cart cart) {
        this.socket = socket;
        this.shop = shop;
        this.cart = cart;
    }

    public Socket getSocket() {
        return socket;
    }

    public Shop getShop() {
        return shop;
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String serverMessage = in.readLine();
            System.out.println(serverMessage);

            Scanner scanner = new Scanner(System.in);
            String clientMessage = "";

            while (!clientMessage.equals("0")) {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);

                    if (line.trim().toLowerCase().contains("enter")) {
                        break;
                    }
                }

                System.out.print("> ");
                clientMessage = scanner.nextLine();
                out.println(clientMessage);
            }

            scanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
