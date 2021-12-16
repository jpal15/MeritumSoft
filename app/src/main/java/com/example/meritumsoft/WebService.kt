package com.example.meritumsoft

import retrofit2.Call
import retrofit2.http.GET

interface WebService {

    @GET("app_data/club_teams_5.xml")
    fun getFeed(): Call<ClubTeams>
}