package com.example.retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY="f25ac15863574310a71e11bb58aacb2b"
interface Api_interface {
    @GET("v2/top-headlines?apiKey=${API_KEY}")
    fun getheadlines(@Query("country")country:String,@Query("page")pageno:Int) : Call<NewsData>
}
object NewsService {
     val newinstance : Api_interface
    init {
        val retrofitbuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
          newinstance= retrofitbuilder.create(Api_interface::class.java)}
    }