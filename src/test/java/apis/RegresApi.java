package apis;

import apiModals.Regres;
import autoFramework.ApiBase;
import com.google.gson.Gson;
import io.restassured.response.Response;

public class RegresApi extends ApiBase {
    Gson gson = new Gson();
    Response response = null;

    /** Wrapper method for the GET call that returns the class Regres*/
    public Regres GetRegres(String resource) throws Exception {

        try {
            response = Get(resource);
        }
        catch (Exception e)
        {
            throw new Exception("Caught Exception", e);
        }

        return gson.fromJson(response.asString(), Regres.class);
    }
}
