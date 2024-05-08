package com.google.tv.material.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvGridItemSpan
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.itemsIndexed
import androidx.tv.material3.Text

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeGrid() {
    val focusRequester = remember { FocusRequester() }
    val itemClick = {
        focusRequester.saveFocusedChild()
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Column(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .weight(1f)
                .padding(start = 54.dp, top = 0.dp, end = 38.dp, bottom = 12.dp)
        ) {
            TvLazyVerticalGrid(
                columns = TvGridCells.Fixed(4),
                modifier = Modifier
                    .padding(top = 12.dp)
                    .focusRequester(focusRequester)
                    .focusRestorer(),
                contentPadding = PaddingValues(vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item(span = { TvGridItemSpan(4) }) {
                    Text(text = "Foundations")
                }

                itemsIndexed(foundations) { index, item ->
                    ComponentsGridCard(component = item, onClick = itemClick)
                }

                item(span = { TvGridItemSpan(4) }) {
                    Text(text = "Components")
                }

                itemsIndexed(components) { index, item ->
                    ComponentsGridCard(component = item, onClick = itemClick)
                }

                item(span = { TvGridItemSpan(4) }) {
                    Text(text = "Components (planned)")
                }

                itemsIndexed(componentsPlanned) { index, item ->
                    ComponentsGridCard(component = item, onClick = itemClick)
                }
            }
        }
    }
}