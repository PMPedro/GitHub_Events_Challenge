package com.example.ecabs_challenge_pedro_martins.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecabs_challenge_pedro_martins.Data.Api.GithubRepository
import com.example.ecabs_challenge_pedro_martins.Data.utils.Result
import com.example.ecabs_challenge_pedro_martins.Data.utils.UiState
import com.example.ecabs_challenge_pedro_martins.Model.GithubEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListEventsViewModel @Inject constructor(
    private val repository : GithubRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<GithubEvent>>>(
        UiState.Loading
    )
    val uiState: StateFlow<UiState<List<GithubEvent>>> = _uiState

    private var lastEventId: String? = null

    init {
        loadInitialData()
        startPollingEvery10Seconds()
    }
    private fun loadInitialData(){
        val allowedTypes = listOf(
            "PushEvent",
            "WatchEvent",
            "IssuesEvent",
            "PullRequestEvent",
            "ForkEvent"
        )


        viewModelScope.launch {
            _uiState.value = UiState.Loading

            when(val result = repository.getEvents()){
                is Result.Success -> {
                    val firstFive = result.data.filter {
                        it.type in allowedTypes
                    }

                    _uiState.value = UiState.Success(firstFive)

                    lastEventId = firstFive.firstOrNull()?.id
                }
                is Result.Error -> {
                    _uiState.value = UiState.Error(
                        result.exception.message ?: "Unknown error"
                    )
                }
            }
        }
    }

    private fun startPollingEvery10Seconds(){
        viewModelScope.launch {
            while (isActive) {
                delay(10_000)

                when (val result = repository.getEvents()){
                    is Result.Success -> {
                        val event = result.data

                        val newEvents = lastEventId?.let {
                            lastEventId->
                            event.takeWhile {
                                it.id != lastEventId
                            }
                        } ?: emptyList()

                        if(newEvents.isNotEmpty()){
                            val current = (_uiState.value as? UiState.Success)?.data ?: emptyList()

                            _uiState.value = UiState.Success(newEvents + current)

                            lastEventId = event.firstOrNull()?.id
                        }
                    }
                    is Result.Error -> {
                        _uiState.value = UiState.Error (result.exception.message ?: "Uknown error")
                    }
                }
            }
        }
    }

    fun setEvent(
        event: GithubEvent
    ){
        repository.setSelectedEvent(event)
    }
}
