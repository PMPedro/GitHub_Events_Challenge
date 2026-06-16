package com.example.ecabs_challenge_pedro_martins.Data.Dagger

import com.example.ecabs_challenge_pedro_martins.Data.Api.NetworkModule
import com.example.ecabs_challenge_pedro_martins.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)
}