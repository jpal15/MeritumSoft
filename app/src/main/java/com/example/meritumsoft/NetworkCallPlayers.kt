package com.example.meritumsoft

import android.util.Log
import androidx.core.util.Consumer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object NetworkCallPlayers {
    @JvmStatic
    fun getPlayerData(onSuccess: Consumer<ClubTeams>) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.eclecticasystems.com/esports2/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()

        val service = retrofit.create(WebService::class.java)
        val call = service.getFeed()

        call.enqueue(object : Callback<ClubTeams> {
            override fun onResponse(call: Call<ClubTeams>, response: Response<ClubTeams>) {
                if (response.code() == 200) {
                    onSuccess.accept(response.body())
                }
            }
            override fun onFailure(call: Call<ClubTeams>, t: Throwable) {
                Log.e("123", "error on getting player data " + t.printStackTrace())
            }
        })
    }
}