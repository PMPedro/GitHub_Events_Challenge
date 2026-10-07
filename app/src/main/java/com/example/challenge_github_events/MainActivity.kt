package com.example.ecabs_challenge_pedro_martins

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.ecabs_challenge_pedro_martins.Data.Dagger.GithubApplication
import com.example.ecabs_challenge_pedro_martins.Navigation.NavigationGraph
import com.example.ecabs_challenge_pedro_martins.ViewModel.EventDetailViewModel
import com.example.ecabs_challenge_pedro_martins.ViewModel.EventDetailViewModelFactory
import com.example.ecabs_challenge_pedro_martins.ViewModel.ListEventsViewModel
import com.example.ecabs_challenge_pedro_martins.ViewModel.ListEventsViewModelFactory
import com.example.ecabs_challenge_pedro_martins.ui.theme.ECabs_Challenge_PedroMartinsTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject lateinit var listEventsViewModelFactory: ListEventsViewModelFactory
    @Inject lateinit var eventDetailViewModelFactory: EventDetailViewModelFactory

    private val listEventsViewModel by viewModels<ListEventsViewModel> {
        listEventsViewModelFactory
    }
    private val eventsDetailViewModel by viewModels<EventDetailViewModel> {
        eventDetailViewModelFactory
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        (application as GithubApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            ECabs_Challenge_PedroMartinsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    NavigationGraph(
                        navController = navController ,
                        listEventsViewModel = listEventsViewModel,
                        eventsViewModel = eventsDetailViewModel

                    )
                }
            }
        }
    }
}