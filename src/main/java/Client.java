import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private String id;
    private String name;
    private String email;
    private Cart cart;

    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int port = 6666;

        try (Socket socket = new Socket(hostName, port)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String serverMessage = in.readLine();
            System.out.println(serverMessage);

            Scanner scanner = new Scanner(System.in);
            String clientMessage = "";

            while (true) {
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

                if (clientMessage.equalsIgnoreCase("exit") || clientMessage.equals("0")) {
                    break;
                }
            }

            scanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

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
