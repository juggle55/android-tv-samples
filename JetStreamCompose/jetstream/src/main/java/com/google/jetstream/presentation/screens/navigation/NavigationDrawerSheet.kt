package com.google.jetstream.presentation.screens.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tv
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ModalNavigationDrawer
import androidx.tv.material3.rememberDrawerState
import com.google.jetstream.presentation.screens.Screens
import com.google.jetstream.presentation.theme.JetStreamTheme

@Composable
fun NavigationDrawerSheet(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    content: @Composable () -> Unit
) {
    var selectedTabIndex = 0
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                NavigationItem(
                    drawerValue = it,
                    icon = Icons.Default.Home,
                    text = "Home",
                    selected = selectedTabIndex == 0,
                    onClick = {
                        navController.navigate(Screens.Dashboard())
                        drawerState.setValue(DrawerValue.Closed)
                        selectedTabIndex = 0
                    })
                NavigationItem(
                    drawerValue = it,
                    icon = Icons.Default.Tv,
                    text = "Channels",
                    selected = selectedTabIndex == 1,
                    onClick = {
                        navController.navigate(Screens.Channels())
                        drawerState.setValue(DrawerValue.Closed)
                        selectedTabIndex = 1
                    })
                NavigationItem(
                    drawerValue = it,
                    icon = Icons.Default.Settings,
                    text = "Channels",
                    selected = selectedTabIndex == 2,
                    onClick = {
                        navController.navigate(Screens.Profile())
                        drawerState.setValue(DrawerValue.Closed)
                        selectedTabIndex = 2
                    })
//                NavigationItem(it, Icons.Default.Settings, "Settings") {
//                    navController.navigate(Screens.Profile())
//                }
            }
        },

        content = content
    )
}

@Preview(device = Devices.TV_1080p)
@Composable
fun NavigationDrawerSheetPreview() {
    val navController = rememberNavController()
    JetStreamTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
            NavigationDrawerSheet(content = {}, navController = navController)
        }
    }
}