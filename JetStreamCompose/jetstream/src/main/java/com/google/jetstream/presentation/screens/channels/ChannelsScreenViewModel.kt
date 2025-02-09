package com.google.jetstream.presentation.screens.channels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.jetstream.data.entities.ChannelList
import com.google.jetstream.data.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class ChannelsScreenViewModel @Inject constructor(
    movieRepository: MovieRepository
) : ViewModel() {

    val uiState = movieRepository.getChannels().map {
        ChannelsScreenUiState.Ready(channelList = it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ChannelsScreenUiState.Loading
    )
}

sealed interface ChannelsScreenUiState {

    data object Loading : ChannelsScreenUiState
    data class Ready(val channelList: ChannelList) : ChannelsScreenUiState
}
