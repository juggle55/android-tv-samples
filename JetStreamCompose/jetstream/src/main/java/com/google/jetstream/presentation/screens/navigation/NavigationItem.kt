package com.google.jetstream.presentation.screens.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.Icon
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.Text

@Composable
fun NavigationItem(
    drawerValue: DrawerValue,
    color: ImageVector,
    text: String,
    selected: () -> Unit
) {
    OutlinedButton(
        modifier = Modifier
            .padding(16.dp)
            .wrapContentWidth(),
        onClick = { selected() }
    ) {
        Icon(
            imageVector = color,
            contentDescription = null,
            modifier = Modifier.padding(end = 4.dp),
            tint = Color.White
        )
        AnimatedVisibility(visible = drawerValue == DrawerValue.Open) {
            Text(
                modifier = Modifier.padding(end = 4.dp),
                text = text,
                softWrap = false,
                textAlign = TextAlign.Start,
                color = Color.White
            )
        }
    }
}