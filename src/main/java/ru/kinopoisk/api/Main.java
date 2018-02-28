package ru.kinopoisk.api;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class Main {

    private static final String KINOPOISK_API = "https://ext.kinopoisk.ru/ios/5.0.0/";
    private static final String KP_SALT = "IDATevHDS7";
    private static final String KP_GET_FILM_OPERATION = "getKPFilmDetailView?filmID=";

    public static void main(String[] args) {
        try {
            Main.call_me();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String encodeWithSecret(String action, String timestamp) {
        String full = action + timestamp + KP_SALT;
        System.out.println("FULL STRING TO ENCODE: [" + full + "]");
        byte[] digest = new byte[0];
        try {
            digest = MessageDigest.getInstance("MD5").digest(full.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder(digest.length * 2);
        for (byte b : digest) {
            int i = b & 255;
            if (i < 16) {
                stringBuilder.append('0');
            }
            stringBuilder.append(Integer.toHexString(i));
        }
        return stringBuilder.toString();
    }


    private static void call_me() throws Exception {
        int filmID = 464963;
        String getFilmString = KP_GET_FILM_OPERATION + String.valueOf(filmID);
        String fullAPIString = KINOPOISK_API + getFilmString;
        String timestampString = Long.toString(System.currentTimeMillis());
        String currentTimeInFormat = new SimpleDateFormat("HH:mm MM.dd.yyyy", Locale.getDefault()).format(new Date());

        System.out.println("KP DOMEN API: [" + KINOPOISK_API + "]");
        System.out.println("SALT: [" + KP_SALT + "]");
        System.out.println("METHOD: [" + KP_GET_FILM_OPERATION + "]");
        System.out.println("FILM ID: [" + filmID + "]");
        System.out.println("TIMESTAMP: [" + timestampString + "]");
        System.out.println("TIME IN FORMAT: [" + currentTimeInFormat + "]");

        String key = encodeWithSecret(fullAPIString.substring(KINOPOISK_API.length()), timestampString);

        System.out.println("API ENCODED KEY: [" + key + "]");

        String responseBody = "";
        try (CloseableHttpClient httpclient = HttpClients
                .custom()
                .build()) {
            HttpUriRequest request = RequestBuilder.get().
                    setUri(fullAPIString)
                    .setHeader("X-SIGNATURE", key)
                    .setHeader("X-TIMESTAMP", timestampString)
                    .setHeader("device", "android")
                    .setHeader("Accept", "application/json")
                    .setHeader("Android-Api-Version", "22")
                    .setHeader("countryID", "2")
                    .setHeader("ClientID", "55decdcf6d4cd1bcaa1b3856")
                    .setHeader("clientDate", currentTimeInFormat)
                    .setHeader("cityID", "1")
                    .setHeader("Image-Scale", "3")
                    .setHeader("Cache-Control", "max-stale=0")
                    .setHeader(HttpHeaders.USER_AGENT, "Android client (4.4 / api22), ru.kinopoisk/4.2.1 (52)")
                    .build();

            // Create a custom response handler
            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;

            };
            System.out.println("----------------------------------------");
            System.out.println("REQUEST: [" + request.getRequestLine() + "]");
            System.out.println("HEADERS: " + Arrays.toString(request.getAllHeaders()));
            responseBody = httpclient.execute(request, responseHandler);
        }

        System.out.println("----------------------------------------");
        System.out.println("JSON REQUEST: " + responseBody);
        System.out.println("----------------------------------------");
        System.out.println("TEST PARSING DATA:");

        JSONObject object = new JSONObject(responseBody);
        JSONObject data = object.getJSONObject("data");
        String nameRU = data.getString("nameRU");
        String nameEN = data.getString("nameEN");
        String year = data.getString("year");
        String genres = data.getString("genre");
        System.out.println("Название фильма RU: " + nameRU);
        System.out.println("Название фильма EN: " + nameEN);
        System.out.println("Год: " + year);
        System.out.println("Жанры: " + genres);
        responseBody = responseBody.replaceAll("(,\")","\n,\"");
        System.out.println(responseBody);
    }
}

