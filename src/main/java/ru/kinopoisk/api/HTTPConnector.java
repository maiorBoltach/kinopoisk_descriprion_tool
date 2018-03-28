package ru.kinopoisk.api;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HTTPConnector {
    private final CloseableHttpClient httpclient;
    private final HttpUriRequest request;
    private int statusCode;

    public HTTPConnector(HttpUriRequest request) {
        statusCode = 100;
        httpclient = HttpClients.custom().build();
        this.request = request;
    }

    public String execute() throws IOException {
        String responseBody = "";
        ResponseHandler<String> responseHandler = response -> {
            statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;

        };
        responseBody = httpclient.execute(request, responseHandler);
        httpclient.close();
        return responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
