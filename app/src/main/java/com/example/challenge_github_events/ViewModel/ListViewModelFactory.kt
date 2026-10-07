package com.example.ecabs_challenge_pedro_martins.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecabs_challenge_pedro_martins.Data.Api.GithubRepository
import javax.inject.Inject

class ListEventsViewModelFactory @Inject constructor(
    private val repository: GithubRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(ListEventsViewModel::class.java)) {
            return ListEventsViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}