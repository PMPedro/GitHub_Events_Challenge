package com.example.ecabs_challenge_pedro_martins.Navigation

sealed class Screens (val route: String){
    object ListEvents : Screens("listevents")
    object DetailScreen : Screens("detailScreen")
}