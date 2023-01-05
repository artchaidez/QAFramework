package autoFramework;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ApiBase extends AutoLogger {

    Gson gson = new Gson();

    /** Returns the class of a response body. Needs a wrapper method to determine which class is returned. */
    public HttpResponse<String> Post(Object parms, String resource, String apiKey) throws Exception {

        HttpResponse<String> response = null;

        String jsonRequest = gson.toJson(parms);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(resource))
                .header("x-api-key", apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        try
        {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch (Exception e)
        {
            // TODO: How to test this?
            handleStatusCode(response.statusCode());
        }

        apiLog(response, jsonRequest);

        return response;
    }

    /** Returns the int of a status code. NOTE: Created for OppFi assessment*/
    public int PostInt(String parms, String resource, String apiKey) throws Exception {

        int response;

        String jsonRequest = gson.toJson(parms);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(resource))
                .header("x-api-key", apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        try
        {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).statusCode();
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
