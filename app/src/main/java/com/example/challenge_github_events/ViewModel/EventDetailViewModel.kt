package com.example.ecabs_challenge_pedro_martins.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecabs_challenge_pedro_martins.Data.Api.GithubRepository
import com.example.ecabs_challenge_pedro_martins.Model.GithubEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/*private val _selectedEvent = MutableStateFlow<GithubEvent?>(null)
val selectedEvent: StateFlow<GithubEvent?> = _selectedEvent*/

class EventDetailViewModel @Inject constructor(
    private val repository : GithubRepository
) : ViewModel() {
    val selectedEvent: StateFlow<GithubEvent?> = repository.selectedEvent
}