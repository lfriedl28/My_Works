package com.example.lukef_000.final05.Network;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.lukef_000.final05.MainActivity;
import com.example.lukef_000.final05.Objects.StringJoiner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class WebService extends AsyncTask<String, Void, String> { //make sure this extends AsyncTask<String, Void, String>

    /* public static final String GET_ALL_PRODUCTS = "get all products";
     public static final String GET_PRODUCT_INFORMATION = "get productID";
     public static final String GET_PRODUCT_PRICES = "get product prices";
 */
    //Get product with name
    public static final String GET_PRODUCT_WITH_NAME = "get product with name";
    //Get tokens and shit
    public static final String GET_TOKEN = "get token";
    //get pricing with ids
    public static final String GET_PRODUCT_PRICE_WITH_GIVEN_IDS = "get product with given ids";


    public static JSONObject tokenInformation;

    //Create a private constructor
    public WebService(Activity senderActivity){
        this.senderActivity = senderActivity;
    }
    //Create a static variable of type Activity called senderActivity
    private static Activity senderActivity;

    //create an @Override function called doInBackground. it should return a string and it should be protected.
    //It should take in (String... params)
    @Override
    protected String doInBackground(String... params){

        switch (params[0]){
            case GET_PRODUCT_WITH_NAME:
                getProductWithName(params);
                break;
            case GET_TOKEN:
                getToken();
                break;
            case GET_PRODUCT_PRICE_WITH_GIVEN_IDS:
                getProductPriceWithGivenIds(params);
                break;
            default:
                break;
        }
        return "Executed";
    }

    private void getProductWithName(String[] params){
        String url = URL_Constants.getProductURL(params[1]);

        //In main activity
        try {

            HttpURLConnection connection = getUrlConnectionWithHeader(URL_Constants.getAccessTokenHeaderInfo(), new URL(url));

            connection.connect();

            JSONObject result = readJsonObjectFromURL(connection);

            JSONArray allProducts = result.getJSONArray("results");

            MainActivity mainActivity = (MainActivity) senderActivity;

            mainActivity.receivedAllProducts(allProducts);

        } catch (Exception e) {
            e.printStackTrace();
            MainActivity mainActivity = (MainActivity) senderActivity;
            mainActivity.noResponse();

        }

    }

    private JSONObject readJsonObjectFromURL(HttpURLConnection connection) throws IOException, JSONException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        return new JSONObject(readAllLines(rd));
    }

    private JSONObject readJsonObjectFromURL(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try{
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            return new JSONObject(readAllLines(rd));
        } finally {
            is.close();
        }
    }

    private JSONArray readJSONArrayFromURL(String url) throws IOException, JSONException{
        InputStream is = new URL(url).openStream();
        try{
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            return new JSONArray(readAllLines(rd));
        } finally {
            is.close();
        }
    }

    private void getToken(){
        try {
            tokenInformation = sendPostRequestForToken();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            MainActivity ma = (MainActivity) senderActivity;
            ma.webserviceReturned();
        }
    }

    private void getProductPriceWithGivenIds(String[] params){
        String ids = params[1];
        try {
            HttpURLConnection connection = getUrlConnectionWithHeader(URL_Constants.getAccessTokenHeaderInfo(), new URL(URL_Constants.getPricingInfoURL(ids)));
            connection.connect();
            JSONArray pricing = readJsonObjectFromURL(connection).getJSONArray("results");

            String firstRealPrice = "";
            for(int i = 0; i < pricing.length(); i++){
                firstRealPrice = ((JSONObject) pricing.get(i)).get("marketPrice").toString();
                if(!firstRealPrice.equalsIgnoreCase("null")) {
                    break;
                }
            }

            MainActivity mainActivity = (MainActivity) senderActivity;
            mainActivity.recievedPrice(firstRealPrice);

        } catch (IOException e) {
            e.printStackTrace();
            MainActivity mainActivity = (MainActivity) senderActivity;
            mainActivity.noResponse();
        } catch (JSONException e) {
            e.printStackTrace();
            MainActivity mainActivity = (MainActivity) senderActivity;
            mainActivity.noResponse();
        }
    }

    private HttpURLConnection getUrlConnectionWithHeader(Map<String, String> map, URL url) throws IOException{
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        con.setRequestProperty("Authorization", map.get("Authorization"));
        return con;
    }

    private JSONObject sendPostRequestForToken() throws IOException, JSONException {
        URL url = new URL(URL_Constants.TOKEN_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        StringJoiner sj = URL_Constants.getTokenPostCredentials();
        con.setDoOutput(true);

        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        con.setFixedLengthStreamingMode(length);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        con.connect();
        OutputStream os = con.getOutputStream();
        os.write(out);
        BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String result = readAllLines(rd);
        System.out.println(result + "\n\n\n");
        JSONObject jo = new JSONObject(result);
        os.close();
        rd.close();
        return jo;
    }

    public static String readAllLines(BufferedReader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while((cp = rd.read()) != -1) {
            sb.append((char)cp);
        }
        rd.close();
        return sb.toString();
    }

}

