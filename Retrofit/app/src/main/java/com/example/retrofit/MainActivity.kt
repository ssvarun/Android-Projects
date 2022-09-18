package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

const val BASE_URL= "https://newsapi.org/"
class MainActivity : AppCompatActivity() {
     lateinit var adapter: MyAdapter
     lateinit var recyclerview:RecyclerView
     var newslist = mutableListOf<Article>()
     var total=-1
     var pageno=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerview=findViewById(R.id.recyclerview)
        recyclerview.setHasFixedSize(true)
        val layoutmanager = LinearLayoutManager(this)
//        layoutmanager.horizontalLayout=false
//        layoutmanager.scrollMultiplier=1.0f
//        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
//
//            override fun onChanged() {
//                // do work here
//                if(total>layoutmanager.itemCount && layoutmanager.findFirstVisibleItemPosition()>=layoutmanager.itemCount-5)
//                {
//                    pageno++
//                    getmydata()
//                }
//            }
//
//        })
        recyclerview.layoutManager=layoutmanager
        adapter=MyAdapter(baseContext,newslist)
        recyclerview.adapter=adapter
        getmydata()
    }

    private fun getmydata() {
        val retrofitdata = NewsService.newinstance.getheadlines("in",pageno)
        // Ctrl + shift + spacebar for the member functions of enqueue
        retrofitdata.enqueue(object : Callback<NewsData>{
            override fun onResponse(call: Call<NewsData>, response: Response<NewsData>) {
                val result = response.body()!!
//                Log.v("varuncode",result.toString()+"Sucess")
                  total=result.totalResults
                  newslist.addAll(result.articles)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<NewsData>, t: Throwable) {
                Log.v("varuncode","Failure occured")
            }
        })
    }
}