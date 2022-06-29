package com.atypon.nosqldemoapplication.databaseconnector;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;
import java.io.UncheckedIOException;

public class DatabaseRestClient {

    private final String credentials;

    private final OkHttpClient okHttpClient = new OkHttpClient();

    public DatabaseRestClient(String credentials) {
        this.credentials = credentials;
    }

    public String postRequest(String requestUrl, String bodyJson) {
        RequestBody requestBody = createPostRequestBody(bodyJson);
        Request request = createPostRequest(requestUrl, requestBody);
        return executeRequest(request);
    }

    private RequestBody createPostRequestBody(String bodyJson) {
        return RequestBody.create(
                MediaType.parse("application/json"),
                bodyJson
        );
    }

    private Request createPostRequest(String requestUrl, RequestBody requestBody) {
        return new Request.Builder()
                .header("Authorization", credentials)
                .url(requestUrl)
                .post(requestBody)
                .build();
    }

    private String executeRequest(Request request) {
        try {
            return okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public String getRequest(String requestUrl) {
        Request request = createGetRequest(requestUrl);
        return getResponseString(request);
    }

    private String getResponseString(Request request) {
        try {
            return okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private Request createGetRequest(String requestUrl) {
        return new Request.Builder()
                .header("Authorization", credentials)
                .url(requestUrl)
                .get()
                .build();
    }
}