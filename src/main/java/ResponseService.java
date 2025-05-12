import com.google.gson.Gson;

public class ResponseService {

    public static String response(Object obj){
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,new Gson().toJsonTree(obj)));
    }
}
