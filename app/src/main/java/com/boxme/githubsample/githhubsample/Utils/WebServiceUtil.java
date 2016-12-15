package com.boxme.githubsample.githhubsample.Utils;

import com.google.gson.GsonBuilder;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vth02 on 15-Dec-16.
 */

public class WebServiceUtil {

    private static OkHttpClient mOkHttpClient;
    private static WebServiceInterface webServiceInstance;

    public static WebServiceInterface getServiceInstance() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                int retryCount = 0;
                Request request = chain.request();
                Response response = chain.proceed(request);
                retryCount++;
                while (!response.isSuccessful() && retryCount < Constants.RETRY_COUNT) {
                    retryCount++;
                    response = chain.proceed(request);
                }
                return response;
            }
        });

        mOkHttpClient = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.WEBSERVICE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
        webServiceInstance = retrofit.create(WebServiceInterface.class);
        return webServiceInstance;
    }
}
