import org.json.JSONObject;
import java.util.Objects;
import java.util.Scanner;

public class UserInterfaceService {
    static Scanner scanner = new Scanner(System.in);
    private Shop shop;

    public UserInterfaceService(Shop shop) {
        this.shop = shop;
    }

    public void Main() {
        boolean exit = false;
        welcome();

        while (!exit) {
            actions();
            System.out.print("Enter option: ");
            String next = scanner.next();
            switch (next) {
                case "1":
                    show();
                    break;
                case "2":
                    add();
                    break;
                case "3":
                    remove();
                    break;
                case "4":
                    registration();
                    break;
                case "5":
                    auth();
                    break;
                case "0":
                    System.out.println("Exit!");
                    exit = true;
                    break;
                default:
                    System.out.println("Enter correct option...");
            }
        }
        scanner.close();
    }

    private void welcome() {
        System.out.println("\n===== Welcome! =====");
    }

    private void actions() {
        System.out.println("====================");
        System.out.println("\nWhat you want to do?");
        System.out.println("1. View products");
        System.out.println("2. Add to list");
        System.out.println("3. Remove from list");
        System.out.println("4. Registration");
        System.out.println("5. Authentication");
        System.out.println("0. Exit");
        System.out.println("====================");
    }

    private void show() {
        shop.show();
    }

    private void add() {
        System.out.println("ADD ITEM");
        System.out.print("Enter Item type(milk,cheese,yogurt): ");
        scanner.nextLine();
        String type = scanner.nextLine();
        System.out.print("Enter Item id(example: 9261): ");
        scanner.nextLine();
        String id = scanner.nextLine();
        System.out.print("Enter Item name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter Item price: ");
        Float price = scanner.nextFloat();
        System.out.print("Enter Item description: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        String fileName = shop.dataFileName;
        FileManagerService.addItemToJSON(type, id, name, price, description, fileName);
        shop.update();
    }

    private void remove() {
        System.out.println("REMOVE ITEM");
        System.out.print("Enter Item type(milk,cheese,yogurt): ");
        scanner.nextLine();
        String type = scanner.nextLine();
        System.out.print("Enter Item id(example: 9261): ");
        scanner.nextLine();
        String id = scanner.nextLine();
        String fileName = shop.dataFileName;
        FileManagerService.removeItemFromJSON(type, id, fileName);
        shop.update();
    }

    private void registration() {
        System.out.println("REGISTRATION");
        String fileName = shop.clientFileName;
        System.out.print("Enter name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        if (FileManagerService.find("email", email, fileName) == null) {
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            FileManagerService.addClientToJSON(String.format("%04d", shop.getClients().size() + 1), email, password, name, fileName);
        }else{
            System.out.println("Account exist!");
        }
        shop.update();
    }

    private void auth() {
        System.out.println("AUTHENTICATION");
        System.out.print("Enter email: ");
        scanner.nextLine();
        String email = scanner.nextLine();
        String fileName = shop.clientFileName;

        if (FileManagerService.find("email", email, fileName) != null) {
            JSONObject findClient = Objects.requireNonNull(FileManagerService.find("email", email, fileName));
            int findClientPassword = findClient.getInt("password");

            boolean pass = false;
            while (!pass) {
                System.out.print("Enter password(Enter 'exit' to comeback): ");
                String password = scanner.nextLine();
                if (Objects.equals(password, "exit")) {
                    pass = true;
                    break;
                }
                if (findClientPassword == password.hashCode()) {
                    pass = true;
                    Client client = new Client(findClient.getString("id"), findClient.getString("email"), findClient.getString("name"));
                    System.out.println("Authentication is finished!!!");
                } else {
                    System.out.println("Incorrect password!");
                }
            }
        }else{
            System.out.println("Not existed Email!");
        }
        shop.update();
    }
}

