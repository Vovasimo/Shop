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

        while(!exit){
            actions();
            System.out.print("Enter option: ");
            Integer nextInt = scanner.nextInt();
            switch(nextInt) {
                case 1:
                    show();
                    break;
                case 2:
                    add();
                    break;
                case 3:
                    remove();
                    break;
                case 0:
                    System.out.println("Exit!");
                    exit = true;
                    break;
                default:
                    System.out.println("Enter correct option.");
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
        System.out.println("0. Exit");
        System.out.println("====================");
    }

    private void show(){
        shop.show();
    }

    private void add() {
        System.out.println("ADD ITEM");
        System.out.print("Enter Item type(milk,cheese,yogurt): ");
        scanner.nextLine();
        String type = scanner.nextLine();
        System.out.print("Enter Item id(example: 9261): ");
        Integer id = scanner.nextInt();
        System.out.print("Enter Item name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter Item price: ");
        Float price = scanner.nextFloat();
        System.out.print("Enter Item description: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        String fileName = shop.dataFileName;
        shop.managerService.addObjectToJSON(type, id, name, price, description, fileName);
        shop.update();
    }

    private void remove() {
        System.out.println("REMOVE ITEM");
        System.out.print("Enter Item type(milk,cheese,yogurt): ");
        scanner.nextLine();
        String type = scanner.nextLine();
        System.out.print("Enter Item id(example: 9261): ");
        Integer id = scanner.nextInt();
        String fileName = shop.dataFileName;
        shop.managerService.removeObjectFromJSON(type, id, fileName);
        shop.update();
    }
}
