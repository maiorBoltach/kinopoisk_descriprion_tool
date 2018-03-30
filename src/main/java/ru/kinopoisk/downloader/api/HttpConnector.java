package ru.kinopoisk.downloader.api;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

class HttpConnector {
    private final CloseableHttpClient httpclient;
    private final HttpUriRequest request;
    private int statusCode;

    HttpConnector(HttpUriRequest request) {
        statusCode = 100;
        httpclient = HttpClients.custom().build();
        this.request = request;
    }

    String execute() throws IOException {
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

    int getStatusCode() {
        return statusCode;
    }
}
