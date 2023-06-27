package com.example.retrofit

import com.example.retrofit.model.Activity
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("gimme/wholesomememes")
     fun getData() : Call<Activity>
}