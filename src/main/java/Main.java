public class Main {
    public static void main(String[] args) {
        System.out.println("WELCOME!");

        String fileName = "src/main/java/Data.json";
        Shop shop = new Shop(fileName);
        UserInterfaceService IO = new UserInterfaceService(shop);
        IO.Main();
    }
}