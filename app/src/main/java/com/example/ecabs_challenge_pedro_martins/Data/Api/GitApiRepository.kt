package com.example.ecabs_challenge_pedro_martins.Data.Api

import com.example.ecabs_challenge_pedro_martins.Data.utils.Result
import com.example.ecabs_challenge_pedro_martins.Model.GithubEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(
    private val api: ApiService
) {
    private val _selectedEvent = MutableStateFlow<GithubEvent?>(null)
    val selectedEvent: StateFlow<GithubEvent?> = _selectedEvent.asStateFlow()

    suspend fun getEvents(): Result<List<GithubEvent>> {

        return try {
            val response = api.getPublicEvents()
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    fun setSelectedEvent(event: GithubEvent) {
        _selectedEvent.value = event
    }

    fun getSelectedEvent(): GithubEvent? {
        return selectedEvent.value
    }


}