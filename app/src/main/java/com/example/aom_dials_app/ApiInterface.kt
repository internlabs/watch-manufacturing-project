package com.example.aom_dials_app

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL= "https://newsapi.org/"
const val API_KEY = "ac1ea72177d34cbb948ca03ca9b3290c"

interface NewsInterface {
    //here we will be declaring our get requests
    //https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=ac1ea72177d34cbb948ca03ca9b3290c
    @GET("/v2/top-headlines?sources=techcrunch&apiKey=$API_KEY")   //GET ANNOTATION TO SPECIFY WE ARE MAKING A GET REQUEST
    //ADD END POINT OF URL TO THE GET ANNOTATION
    fun Getdata(@Query("page")page : Int): Call<MyData>
}

object ApiInterface {
    val newsInstance: NewsInterface

    init {
        //create retrofit builder obj
        val retrofitbuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        newsInstance = retrofitbuilder.create(NewsInterface::class.java)
    }
}