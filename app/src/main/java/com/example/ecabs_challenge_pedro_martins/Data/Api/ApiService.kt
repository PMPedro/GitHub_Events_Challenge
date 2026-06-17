package com.example.ecabs_challenge_pedro_martins.Data.Api

import com.example.ecabs_challenge_pedro_martins.Model.GithubEvent
import retrofit2.http.GET

interface ApiService {
    @GET("events")
    suspend fun getPublicEvents(): List<GithubEvent>
}