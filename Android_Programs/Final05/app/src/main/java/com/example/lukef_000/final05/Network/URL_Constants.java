package com.example.lukef_000.final05.Network;

import com.example.lukef_000.final05.Objects.StringJoiner;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class URL_Constants {

    private static final String PUBLIC_KEY_VALUE = "228C1CB1-FE6E-489C-92B6-7505D610CC79";
    private static final String PUBLIC_KEY_KEY = "client_id";
    private static final String PUBLIC_GRANT_KEY = "grant_type";
    private static final String PUBLIC_GRANT_VALUE = "client_credentials";
    private static final String PRIVATE_KEY_VALUE = "29C22DB0-E718-4FF5-9C06-C08FCBDAC376";
    private static final String PRIVATE_KEY_KEY = "client_secret";

    public static final String TOKEN_URL = "https://api.tcgplayer.com/token";
    public static final String GET_PRODUCTS = "http://api.tcgplayer.com/catalog/products";
    public static final String GET_PRICING_INFORMATION = "http://api.tcgplayer.com/pricing/product/";

    public static String getProductURL(String productName) {
        return GET_PRODUCTS + "?productName=" + productName;
    }

    public static String getPricingInfoURL(String prodIDs){
        return GET_PRICING_INFORMATION + prodIDs;
    }

    private static Map<String, String> getTokenPostCreds(){
        Map<String, String> map = new HashMap<>();
        map.put(PUBLIC_GRANT_KEY, PUBLIC_GRANT_VALUE);
        map.put(PUBLIC_KEY_KEY, PUBLIC_KEY_VALUE);
        map.put(PRIVATE_KEY_KEY, PRIVATE_KEY_VALUE);
        return map;
    }

    public static StringJoiner getTokenPostCredentials() throws UnsupportedEncodingException {
        StringJoiner sj = new StringJoiner("&");
        Map<String, String> map = getTokenPostCreds();
        for(Map.Entry<String, String> entry: map.entrySet()) {
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return sj;
    }

    private static String getAccessToken() throws JSONException {
        return WebService.tokenInformation.getString("access_token");
    }

    public static Map<String, String> getAccessTokenHeaderInfo() throws JSONException {
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "bearer " + getAccessToken());
        return map;
    }



}

