package ru.kinopoisk.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import ru.kinopoisk.api.enums.KinopoiskOperations;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class APIRequester {

    private final RequestBuilder requestString;
    private final String KINOPOISK_API_URL = "https://ext.kinopoisk.ru/ios/5.0.0/";
    private final String KINOPOISK_API_SALT = "IDATevHDS7";

    public APIRequester() {
        requestString = setBasicHeaders();
    }

    private RequestBuilder setBasicHeaders() {
        return RequestBuilder.get()
                .setHeader("device", "android")
                .setHeader("Accept", "application/json")
                .setHeader("Android-Api-Version", "22")
                .setHeader("countryID", "2")
                .setHeader("ClientID", "55decdcf6d4cd1bcaa1b3856")
                .setHeader("cityID", "1")
                .setHeader("Image-Scale", "3")
                .setHeader(HttpHeaders.USER_AGENT, "Android client (4.4 / api22), ru.kinopoisk/4.2.1 (52)");
    }

    private String encodeWithSecret(String methodAction, String timestamp) {
        String full = methodAction + timestamp + KINOPOISK_API_SALT;
        return new String(Hex.encodeHex(DigestUtils.md5(full)));
    }

    private JsonNode getRequest(String requestStr) {
        String result = "";
        Date currentTime = new Date();
        String currentTimeInFormat = new SimpleDateFormat("HH:mm MM.dd.yyyy", Locale.getDefault()).format(currentTime);
        String timestampString = Long.toString(System.currentTimeMillis());
        String key = encodeWithSecret(requestStr, timestampString);
        String uriAdress = KINOPOISK_API_URL + requestStr;
        HttpUriRequest httpRequester = requestString.setUri(uriAdress)
                .setHeader("X-SIGNATURE", key)
                .setHeader("X-TIMESTAMP", timestampString)
                .setHeader("clientDate", currentTimeInFormat)
                .build();
        LoggerClass.getInstanceSummaryLogger().trace("Request API: " + uriAdress);
        LoggerClass.getInstanceSummaryLogger().trace("Request headers: " + Arrays.toString(httpRequester.getAllHeaders()));

        HTTPConnector newConnector = new HTTPConnector(httpRequester);

        try {
            result = newConnector.execute();
        } catch (IOException e) {
            LoggerClass.getInstanceSummaryLogger().error("Can't get response from server '" + e.getMessage() + "', status " + newConnector.getStatusCode() + " ");
            LoggerClass.getInstanceSummaryLogger().error("Response body: '" + result + "'");
        }

        if (newConnector.getStatusCode() == 200) {
            try {
                return new ObjectMapper().readTree(result);
            } catch (IOException e) {
                LoggerClass.getInstanceSummaryLogger().error("Can't convert response to json array '" + e.getMessage());
            }
        }
        return null;
    }

    public JsonNode getFilmInfo(int filmID) {
        String parameterFilmID = "?filmID=";
        String fullAPIString = KinopoiskOperations.FILM_DETAILS.toString() + parameterFilmID + String.valueOf(filmID);
        JsonNode jsonObject = getRequest(fullAPIString);
        checkJsonObject(jsonObject, filmID);
        return jsonObject;
    }


    private void checkJsonObject(JsonNode jsonObject, int id) {
        if (jsonObject == null) {
            LoggerClass.getInstanceSummaryLogger().info("Null JSON (id = '" + id + "')");
            LoggerClass.getInstanceSummaryLoggerID().info(id);
        }
    }
}
