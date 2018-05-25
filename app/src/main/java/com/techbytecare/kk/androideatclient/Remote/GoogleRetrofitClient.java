package com.techbytecare.kk.androideatclient.Remote;


import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by kundan on 1/29/2018.
 */

public class GoogleRetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getGoogleClient(String baseURL)
    {
        if (retrofit == null)   {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
