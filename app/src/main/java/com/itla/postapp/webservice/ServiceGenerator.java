package com.itla.postapp.webservice;

import android.text.TextUtils;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String BASE_URL = "http://itla.hectorvent.com/api/";

    private static ServiceGenerator INSTANCE;
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder;

    private ServiceGenerator(){
        builder = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());
    }

    public static synchronized ServiceGenerator getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ServiceGenerator();
        }

        return INSTANCE;
    }

    public <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
            }
        }

        builder.client(httpClient.build());
        Retrofit retrofit = builder.build();

        return retrofit.create(serviceClass);
    }
}
