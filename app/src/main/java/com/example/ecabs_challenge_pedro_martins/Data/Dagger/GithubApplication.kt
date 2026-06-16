package com.example.ecabs_challenge_pedro_martins.Data.Dagger

import android.app.Application

class GithubApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}