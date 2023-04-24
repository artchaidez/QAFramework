package autoFramework;

import com.google.gson.Gson;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class ApiBase extends AutoLogger {

    Gson gson = new Gson();

    /** Returns the class of a response body. Needs a wrapper method to determine which class is returned. */
    public Response Post(Object parms, String resource, String apiKey) throws Exception {

        Response response = null;

        String jsonRequest = gson.toJson(parms);

        try {
            response =
                    given()
                        .contentType("application/json")
                        .body(jsonRequest)
                        .header("x-api-key", apiKey)
                    .when()
                        .post(resource)
                    .then()
                        .statusCode(200) // TODO: Handles exception; try/catch and handleStatusCode(int statusCode) could be removed
                        .extract()
                        .response();

        }
        catch (Exception e)
        {
            // TODO: handleStatusCode(int statusCode) could be removed, or this method should be reworked
            handleStatusCode(response.statusCode());
        }

        apiLog(response, jsonRequest, resource, "POST");

        return response;
    }

    /** Returns the int of a status code. NOTE: Created for OppFi assessment*/
    public int PostInt(String parms, String resource, String apiKey) throws Exception {

        int response = 0;

        String jsonRequest = gson.toJson(parms);

        try {
            response =
                    given()
                            .contentType("application/json")
                            .body(jsonRequest)
                            .header("x-api-key", apiKey)
                    .when()
                            .post(resource)
                    .then()
                            .extract()
                            .response().statusCode();

        }
        catch (Exception e)
        {
            Info("Something went wrong with the POST.");
            throw new Exception("Caught Exception", e);
        }

        return response;
    }

    private void handleStatusCode(int statusCode) throws Exception
    {
        if (statusCode >= 300 && statusCode < 400)
        {
            throw new Exception("Action needed to fulfil request - Status code: " + statusCode);
        } else if (statusCode >= 400 && statusCode < 500) {
            throw new Exception("Client error - Status code: " + statusCode);
        } else {
            throw new Exception("Server error - Status code: " + statusCode);
        }
    }
}
