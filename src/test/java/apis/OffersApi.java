package apis;

import apiModals.Offer;
import autoFramework.ApiBase;
import com.google.gson.Gson;
import io.restassured.response.Response;

public class OffersApi extends ApiBase {
    Gson gson = new Gson();
    Response response = null;

    /** Wrapper method for the post call that returns the class Offer
     * @param offerRequest Offer object that will be converted to json
     * @param resource URI
     * @param apiKey apiKey needed for URI*/
    public Offer PostOffers(Offer offerRequest, String resource, String apiKey) throws Exception {

        try {
            response = Post(offerRequest, resource, apiKey);
        }
        catch (Exception e)
        {
            throw new Exception("Caught Exception", e);
        }

        return gson.fromJson(response.asString(), Offer.class);
    }

    /** Wrapper method for the post call that returns the int of a status code.
     * NOTE: Created for OppFi assessment*/
    public int PostOffersInt(String offerRequest, String resource, String apiKey) throws Exception {

        int response;

        try {
            response = PostInt(offerRequest, resource, apiKey);
        }
        catch (Exception e)
        {
            throw new Exception("Caught Exception", e);
        }

        return response;
    }
}
