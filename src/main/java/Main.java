public class Main {
    public static void main(String[] args) {
        System.out.println("WELCOME!");

        String fileName = "src/main/java/Data.json";
        Shop shop = new Shop(fileName);
        FileManagerService managerService = new FileManagerService();

        shop.show();
        Milk milk = new Milk(1322,"XXXXXXXX", 7.89F, "Something about this milk");
        managerService.addObjectToFile(milk, fileName);
        shop.update();
        shop.show();

    }
}