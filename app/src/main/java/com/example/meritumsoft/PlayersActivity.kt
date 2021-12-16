package com.example.meritumsoft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.meritumsoft.databinding.ActivityPlayersBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class PlayersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayersBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.eclecticasystems.com/esports2/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()

        val service = retrofit.create(WebService::class.java)
        val call = service.getFeed()

        call.enqueue(object : Callback<ClubTeams> {
            override fun onResponse(call: Call<ClubTeams>, response: Response<ClubTeams>) {
                if (response.code() == 200) {
                    Log.i("123", "reponse " + response.body())
                }
            }
            override fun onFailure(call: Call<ClubTeams>, t: Throwable) {
                Log.i("123", "error " + t.printStackTrace())
            }
        })
    }
}