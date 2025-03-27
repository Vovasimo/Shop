import java.util.Scanner;

public class UserInterfaceService {
    Scanner scanner = new Scanner(System.in);

    public static void main() {
        boolean exit = false;
        welcome();

        while(!exit){
            actions();

        }
    }

    public static void welcome() {
        System.out.println("\n===== Welcome! =====");
    }

    public static void actions() {
        System.out.println("\nWhat you want to do?");
        System.out.println("1. View products");
        System.out.println("2. Cart");
        System.out.println("3. Payment");
        System.out.println("0. Logout");
        System.out.println("====================");
    }

    public static void Cart() {

        System.out.println("\nWhat you want to do?");
        System.out.println("1. View products");
        System.out.println("2. Cart");
        System.out.println("3. Payment");
        System.out.println("0. Logout");
        System.out.println("====================");
    }

    public static void remove() {

    }
}