package com.google.jetstream.presentation.screens.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.DefaultTintColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ButtonShape
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.Text
import androidx.tv.material3.rememberDrawerState
import com.google.jetstream.JetStreamApplication
import com.google.jetstream.presentation.theme.JetStreamButtonShape
import com.google.jetstream.presentation.theme.JetStreamCardShape
import com.google.jetstream.presentation.theme.JetStreamTheme

@Composable
fun NavigationItem(
    drawerValue: DrawerValue,
    icon: ImageVector,
    text: String,
    selected: Boolean,
    onClick: () -> Unit,

    activeColor: Color = Color(0xFFE5E1E6),
    inactiveColor: Color = MaterialTheme.colorScheme.secondaryContainer,
) {

 //   val focusRequester = FocusRequester()
    //var isTabRowFocused by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (selected) activeColor else inactiveColor,
        label = "pillColor"
    )
    Button(
        { onClick() }, Modifier
            .padding(16.dp)
          //  .focusRequester(focusRequester)
            .wrapContentWidth(),
//            .onFocusChanged {
//                isTabRowFocused = it.isFocused || it.hasFocus
//            },
            //shape = ButtonDefaults.shape(shape = JetStreamButtonShape),
            colors = ButtonDefaults.colors(
                containerColor = Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.inverseSurface,
                contentColor = color
            )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            //modifier = Modifier.padding(end = 4.dp),
            //tint = Color.White
        )

        AnimatedVisibility(visible = drawerValue == DrawerValue.Open) {
            Text(
                modifier = Modifier.padding(end = 4.dp),
                text = text,
                softWrap = false,
                textAlign = TextAlign.Start,

            )
        }
//        LaunchedEffect(Unit) {
//            if(selected) {
//                focusRequester.requestFocus()
//            }
//        }

    }
}

@Preview(device = Devices.TV_1080p)
@Composable
fun NavigationItemPreview() {
    JetStreamTheme {
        Row(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
            Box {
                NavigationItem(DrawerValue.Open, Icons.Default.Home, "Home", true, {})
            }
            Box {
                NavigationItem(DrawerValue.Closed, Icons.Default.Home, "Home", false, {})
            }
            Box {
                NavigationItem(DrawerValue.Open, Icons.Default.Home, "Home", false, {})
            }
            Box {
                NavigationItem(DrawerValue.Closed, Icons.Default.Home, "Home", false, {})
            }
        }
    }
}