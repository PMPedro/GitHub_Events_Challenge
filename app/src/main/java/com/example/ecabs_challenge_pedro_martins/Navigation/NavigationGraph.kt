package com.example.ecabs_challenge_pedro_martins.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ecabs_challenge_pedro_martins.View.EventDetailView
import com.example.ecabs_challenge_pedro_martins.View.ListEventsScreen
import com.example.ecabs_challenge_pedro_martins.ViewModel.EventDetailViewModel
import com.example.ecabs_challenge_pedro_martins.ViewModel.ListEventsViewModel
import com.example.ecabs_challenge_pedro_martins.ViewModel.ListEventsViewModelFactory
import javax.inject.Inject

@Composable
fun NavigationGrath(
    navController: NavHostController,
    listEventsViewModel : ListEventsViewModel ,
    eventsViewModel: EventDetailViewModel
) {


    NavHost(
        navController = navController,
        startDestination = Screens.ListEvents.route
    )
    {
        composable(Screens.ListEvents.route) {
            ListEventsScreen(
                viewModel = listEventsViewModel ,
                onDetailClickNavigation = {
                    navController.navigate(Screens.DetailScreen.route)
                }
                )
        }

        composable(Screens.DetailScreen.route) {
            EventDetailView(
                viewModel = eventsViewModel
            )
        }
    }
}