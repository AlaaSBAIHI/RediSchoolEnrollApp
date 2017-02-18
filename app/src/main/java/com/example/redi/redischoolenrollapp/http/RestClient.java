package com.example.redi.redischoolenrollapp.http;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by ReDI on 2/5/2017.
 */

public class RestClient {
    /*
        private static final String URL = "http://192.168.56.1:8080/api/";
    */
    private static final String URL = "https://redi-api.herokuapp.com";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private static final String AUTHORIZATION_KEY = "authorization";
    private static final String ACCEPT_KEY = "accept";// Response Type
    private static final String ACCEPT_VALUE = "application/json";

    private static final String CONTENT_TYPE_KEY = "content-type"; // Request Type
    private static final String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";


    private static final RestClient instance = new RestClient();

    private OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();

    private Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());


    private RestClient() {

    }

    public static RestClient getInstance() {
        return instance;
    }


    public <S> S createService(Class<S> serviceClass) {
        final String credentials = USERNAME + ":" + PASSWORD;
        final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(),
                Base64.NO_WRAP);

        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder reqBuilder = original.newBuilder()
                        .header(AUTHORIZATION_KEY, basic)
                        //.header(ACCEPT_KEY, ACCEPT_VALUE)
                        //   .header(CONTENT_TYPE_KEY,CONTENT_TYPE_VALUE)
                        .method(original.method(), original.body());

                Request request = reqBuilder.build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = builder.client(httpClientBuilder.build()).build();
        return retrofit.create(serviceClass);
    }

}
