package com.google.jetstream.presentation.screens.channels

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.google.jetstream.data.entities.Channel
import com.google.jetstream.data.entities.ChannelList
import com.google.jetstream.presentation.common.Loading
import com.google.jetstream.presentation.common.MovieCard
import com.google.jetstream.presentation.screens.dashboard.rememberChildPadding
import com.google.jetstream.presentation.utils.GradientBg

@Composable
fun ChannelsScreen(
    gridColumns: Int = 4,
    onChannelClick: (channel: Channel) -> Unit,
    //onScroll: (isTopBarVisible: Boolean) -> Unit,
    channelsScreenViewModel: ChannelsScreenViewModel = hiltViewModel()
) {
    val uiState by channelsScreenViewModel.uiState.collectAsStateWithLifecycle()

    when (val s = uiState) {
        ChannelsScreenUiState.Loading -> {
            Loading(modifier = Modifier.fillMaxSize())
        }

        is ChannelsScreenUiState.Ready -> {
            val channelList = remember { s.channelList }
            Catalog(
                gridColumns = gridColumns,
                channels = channelList,
                onChannelClick = onChannelClick,
                //onScroll = onScroll,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Catalog(
    channels: ChannelList,
    modifier: Modifier = Modifier,
    gridColumns: Int = 4,
    onChannelClick: (channel: Channel) -> Unit,
) {
    val childPadding = rememberChildPadding()
    val lazyGridState = rememberLazyGridState()


    var lastFocusedChannel by rememberSaveable { mutableStateOf<Int?>(null) }
    AnimatedContent(
        targetState = channels,
        modifier = Modifier
            .padding(horizontal = childPadding.start)
            .padding(top = childPadding.top),
        label = "",
    ) { it ->
        LazyVerticalGrid(
            state = lazyGridState,
            modifier = modifier,
            columns = GridCells.Fixed(gridColumns),
        ) {
            itemsIndexed(it) { index, channel ->
                var isFocused by remember { mutableStateOf(false) }
                val focusRequester = remember { FocusRequester() }
                val isItemToFocus = lastFocusedChannel == channel.hashCode()
                MovieCard(

                    onClick = {
                        onChannelClick(channel)
                        isFocused = true
                        lastFocusedChannel = channel.hashCode()
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .aspectRatio(16 / 9f)
                        .onFocusChanged {
                            isFocused = it.isFocused || it.hasFocus
                            if (isFocused && !isItemToFocus) {
                                lastFocusedChannel = null
                            }
                        }
                        .focusProperties {
                            if (index % gridColumns == 0) {
                                left = FocusRequester.Cancel
                            }
                        }
                        .focusRequester(focusRequester)
                        .onGloballyPositioned {
                            if(isItemToFocus) {
                                focusRequester.requestFocus()
                            }
                        }

                ) {
                    val itemAlpha by animateFloatAsState(
                        targetValue = if (isFocused) .6f else 0.2f,
                        label = ""
                    )
                    val textColor = if (isFocused) Color.White else Color.White

                    Box(contentAlignment = Alignment.Center) {
                        Box(modifier = Modifier.alpha(itemAlpha)) {
                            GradientBg()
                        }
                        Text(
                            text = channel.name,
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = textColor,
                            )
                        )
                    }
                }
            }
        }
    }
}
