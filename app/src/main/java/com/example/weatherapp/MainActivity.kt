package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.navigation.AppNavHost
import com.example.weatherapp.navigation.Destination
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                val navController = rememberNavController()
                val startDestination = Destination.WEATHER
                var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

                val navOptionsBuilder: NavOptionsBuilder.() -> Unit = {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                            Destination.entries.forEachIndexed { index, destination ->
                                NavigationBarItem(
                                    selected = selectedDestination == index,
                                    onClick = {
                                        navController.navigate(route = destination.route, navOptionsBuilder)
                                        selectedDestination = index
                                    },
                                    icon = {
                                        Icon(destination.icon,
                                            contentDescription = null
                                        )
                                    },
                                    label = {
                                        Text(stringResource(destination.labelRes))
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        startDestination = startDestination,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}