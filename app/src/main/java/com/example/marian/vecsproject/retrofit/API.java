package com.example.marian.vecsproject.retrofit;

import com.example.marian.vecsproject.models.MainRresponse;
import com.example.marian.vecsproject.models.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Marian on 5/10/2018.
 */

public interface API
{
    @POST("login")
    Call<MainRresponse> login(@Body UserModel user);


//    @POST("login")
//    Call<UserModel> login(@Path("login") String login);

   }
