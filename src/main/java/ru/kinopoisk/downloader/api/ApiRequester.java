package ru.kinopoisk.downloader.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import ru.kinopoisk.downloader.logger.LoggerClass;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

class ApiRequester {
    private static volatile ApiRequester _instance = null;
    private final RequestBuilder requestString = setBasicHeaders();
    private final String KINOPOISK_API_URL = "https://ext.kinopoisk.ru/ios/5.0.0/";
    private final String KINOPOISK_API_SALT = "IDATevHDS7";

    static ApiRequester getInstance() {
        ApiRequester localInstance = _instance;
        if (localInstance == null) {
            synchronized (ApiRequester.class) {
                localInstance = _instance;
                if (localInstance == null) {
                    _instance = localInstance = new ApiRequester();
                }
            }
        }
        return localInstance;
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

    JsonNode getRequest(String requestStr) {
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

        HttpConnector newConnector = new HttpConnector(httpRequester);

        try {
            result = newConnector.execute();
            LoggerClass.getInstanceSummaryLogger().info("Response body: '" + result + "'");
        } catch (IOException e) {
            LoggerClass.getInstanceSummaryLogger().error("Can't get response from server '" + e.getMessage()
                    + "', status " + newConnector.getStatusCode() + " ");
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
}
