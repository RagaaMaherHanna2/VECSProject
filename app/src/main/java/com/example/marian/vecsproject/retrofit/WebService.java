package com.example.marian.vecsproject.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Marian on 5/10/2018.
 */

public class WebService
{
    private static WebService instance;
    private API api;

    public WebService()
    {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(VECSapi.MAIN_URL).build();

         api = retrofit.create(API.class);



//        if (retrofit==null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
    }

    public  static WebService getInstance (){
        if(instance == null){
            instance = new WebService() ;
        }
        return instance;
    }
    public  API getApi (){
        return api;
    }
}
