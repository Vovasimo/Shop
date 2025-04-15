import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Server {
    int port;
    ServerSocket serverSocket;
    static Shop shop;

    public Server(int port, Shop shop) throws IOException {
        this.serverSocket = new ServerSocket(port);
        Server.shop = shop;
    }

    public static void main(String[] args) throws IOException {
        String fileName = "src/main/java/Data.json";
        String clientFileName = "src/main/java/Clients.json";
        Shop shop = new Shop(fileName,clientFileName);

        Server server = new Server(6666, shop);
        Socket client = server.serverSocket.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);

        welcome(out);
        mainLoop(in,out);
    }

    public static void mainLoop(BufferedReader in, PrintWriter out) throws IOException {
        boolean exit = false;
        welcome(out);

        while (!exit) {
            actions(out);
            out.print("Enter option: ");
            String next = in.readLine();
            System.out.println(next);
            switch (next) {
                case "1":
                    show(out);
                    break;
                case "2":
                    add(in,out);
                    break;
                case "3":
                    remove(in,out);
                    break;
                case "4":
                    registration(in,out);
                    break;
                case "5":
                    auth(in,out);
                    break;
                case "0":
                    out.println("Exit!");
                    exit = true;
                    break;
                default:
                    out.println("Enter correct option...");
            }
        }
        in.close();
    }

    private static void welcome(PrintWriter out) {
        out.println("===== Welcome! =====");
    }

    private static void actions(PrintWriter out) {
        out.println("====================");
        out.println("What you want to do?");
        out.println("1. View products");
        out.println("2. Add to list");
        out.println("3. Remove from list");
        out.println("4. Registration");
        out.println("5. Authentication");
        out.println("0. Exit");
        out.println("====================");
    }

    private static void show(PrintWriter out) {
        out.println(shop.showInString());
    }

    private static void add(BufferedReader in, PrintWriter out) throws IOException {
        out.println("ADD ITEM");
        out.print("Enter Item type(milk,cheese,yogurt): ");
        String type = in.readLine();
        out.print("Enter Item id(example: 9261): ");
        String id = in.readLine();
        out.print("Enter Item name: ");
        String name = in.readLine();
        out.print("Enter Item price: ");
        Float price = Float.valueOf(in.readLine());
        out.print("Enter Item description: ");
        String description = in.readLine();
        String fileName = shop.dataFileName;
        FileManagerService.addItemToJSON(type, id, name, price, description, fileName);
        shop.update();
    }

    private static void remove(BufferedReader in, PrintWriter out) throws IOException {
        out.println("REMOVE ITEM");
        out.print("Enter Item type(milk,cheese,yogurt): ");
        String type = in.readLine();
        out.print("Enter Item id(example: 9261): ");
        String id = in.readLine();
        String fileName = shop.dataFileName;
        FileManagerService.removeItemFromJSON(type, id, fileName);
        shop.update();
    }

    private static void registration(BufferedReader in, PrintWriter out) throws IOException {
        out.println("REGISTRATION");
        String fileName = shop.clientFileName;
        out.print("Enter name: ");
        String name = in.readLine();
        out.print("Enter email: ");
        String email = in.readLine();
        if (FileManagerService.find("email", email, fileName) == null) {
            out.print("Enter password: ");
            String password = in.readLine();
            FileManagerService.addClientToJSON(String.format("%04d", shop.getClients().size() + 1), email, password, name, fileName);
        }else{
            out.println("Account exist!");
        }
        shop.update();
    }

    private static void auth(BufferedReader in, PrintWriter out) throws IOException {
        out.println("AUTHENTICATION");
        out.print("Enter email: ");
        String email = in.readLine();
        String fileName = shop.clientFileName;

        if (FileManagerService.find("email", email, fileName) != null) {
            JSONObject findClient = Objects.requireNonNull(FileManagerService.find("email", email, fileName));
            int findClientPassword = findClient.getInt("password");

            boolean pass = false;
            while (!pass) {
                out.print("Enter password(Enter 'exit' to comeback): ");
                String password = in.readLine();
                if (Objects.equals(password, "exit")) {
                    pass = true;
                    break;
                }
                if (findClientPassword == password.hashCode()) {
                    pass = true;
                    Client client = new Client(findClient.getString("id"), findClient.getString("email"), findClient.getString("name"));
                    out.println("Authentication is finished!!!");
                } else {
                    out.println("Incorrect password!");
                }
            }
        }else{
            out.println("Not existed Email!");
        }
        shop.update();
    }
}
