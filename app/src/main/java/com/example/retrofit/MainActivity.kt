package com.example.retrofit
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.retrofit.model.Activity
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()
        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener{
            loadData()
        }


    }
    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://meme-api.com/") // Replace with your API base URL
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON parsing
            .build()

        val apiService = retrofit.create(ApiInterface::class.java)
        val call = apiService.getData()
        call.enqueue(object: Callback<Activity>{
            override fun onResponse(call: Call<Activity>, response: Response<Activity>) {
                val iv = findViewById<ImageView>(R.id.iv)
                if( response.body()?.url != null ){
                    Picasso.get().load(response.body()?.url).into(iv)
                }
                val tv = findViewById<TextView>(R.id.tv)
                tv.text = response.body()?.title
                Log.d("msg", response.body().toString())
            }

            override fun onFailure(call: Call<Activity>, t: Throwable) {
                TODO("Not yet implemented")
                Log.d("msg",t.message.toString())
            }

        })
    }
}