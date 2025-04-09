public class Main {
    public static void main(String[] args) {
        System.out.println("WELCOME!");

        String fileName = "src/main/java/Data.json";
        String clientFileName = "src/main/java/Clients.json";
        Shop shop = new Shop(fileName,clientFileName);
        UserInterfaceService IO = new UserInterfaceService(shop);
        IO.Main();
    }
}