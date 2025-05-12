import com.google.gson.Gson;
import org.json.JSONObject;

import static spark.Spark.*;

public class Server {
    public static void main(String[] args) {
        staticFiles.location("/public");
        String fileName = "src/main/java/Data.json";
        String clientFileName = "src/main/java/Clients.json";
        Shop shop = new Shop(fileName,clientFileName);

        get("/hello", (req, res) -> "Hello, world");

        get("/hello/:name", (req, res) -> {
            return "Hello, " + req.params(":name");
        });

        get("/products", (req, res) -> {
            res.type("application/json");
            return ResponseService.response(shop.getList());
        });

        get("/products/:id", (req, res) -> {
            res.type("application/json");

            JSONObject foundItem = FileManagerService.findAndReturn("id",req.params(":id"),shop.dataFileName);

            if(foundItem == null){
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,"Item not found!"));
            }else{
                return ResponseService.response(foundItem.toMap());
            }

        });

        get("/cart", (req, res) -> {
            res.type("application/json");
            return ResponseService.response(shop.cartService.getCartItems(req));
        });

        post("/cart/add", (req, res) -> {
            try{
                shop.cartService.addToCart(req,new Item(new JSONObject(req.body())));
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,"Item added!"));
            }catch(Exception e){
                e.printStackTrace();
                res.status(500);
                return "Server error: " + e.getMessage();
            }

        });

        delete("/cart/delete", (req, res) -> {
            try{
                shop.cartService.removeFromCart(req,new Item(new JSONObject(req.body())));
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,"Item deleted!"));
            }catch(Exception e){
                e.printStackTrace();
                res.status(500);
                return "Server error: " + e.getMessage();
            }

        });
    }
}

