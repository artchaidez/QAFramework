package apis;

import apiModals.RegresListUsers;
import apiModals.RegresSingleUser;
import apiModals.RegresUser;
import autoFramework.ApiBase;
import com.google.gson.Gson;
import io.restassured.response.Response;

public class RegresApi extends ApiBase {
    Gson gson = new Gson();
    Response response = null;

    /** Wrapper method for the GET call that returns the class RegresSingleUser*/
    public RegresSingleUser GetSingleRegres(String resource) throws Exception
    {
        try {
            response = Get(resource);
        } catch (Exception e) {
            throw new Exception("Caught Exception", e);
        }

        return gson.fromJson(response.asString(), RegresSingleUser.class);
    }

    /** Wrapper method for the GET call that returns the class RegresListUsers*/
    public RegresListUsers GetListRegres(String resource) throws Exception
    {
        try {
            response = Get(resource);
        } catch (Exception e) {
            throw new Exception("Caught Exception", e);
        }


        return gson.fromJson(response.asString(), RegresListUsers.class);
    }

    /** Wrapper method for the POST call that returns class RegresUser */
    public RegresUser PostRegres(RegresUser regresRequest, String resource) throws Exception
    {
        try {
            response = Post(regresRequest, resource);
        } catch (Exception e) {
            throw new Exception("Caught Exception", e);
        }

        return gson.fromJson(response.asString(), RegresUser.class);
    }

    /** Wrapper method for the PUT call that returns class RegresUser */
    public RegresUser PutRegres(RegresUser regresRequest, String resource) throws Exception
    {
        try {
            response = Put(regresRequest, resource);
        } catch (Exception e) {
            throw new Exception("Caught Exception", e);
        }

        return gson.fromJson(response.asString(), RegresUser.class);
    }

    /** Wrapper method for the DELETE call */
    public void DeleteRegres(String resource) throws Exception
    {
        try {
            Delete(resource);
        } catch (Exception e) {
            throw new Exception("Caught Exception", e);
        }

    }
}
