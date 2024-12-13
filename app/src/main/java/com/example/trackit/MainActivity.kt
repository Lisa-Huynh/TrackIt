package com.example.trackit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trackit.navigation.NavController
import com.example.trackit.navigation.Route
import com.example.trackit.home.HomeScreen
import com.example.trackit.onboarding.login.LoginScreen
import com.example.trackit.onboarding.userinfo.UserInfoScreen
import com.example.trackit.profile.ProfileScreen
import com.example.trackit.ui.theme.AppTheme
import com.example.trackit.wallet.WalletScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val navHostController = rememberNavController()

                val navController by mainViewModel.navigationStream.collectAsState()

                LaunchedEffect(navController) {
                    navController?.let { controller ->
                        when (controller) {
                            NavController.GoBack -> {
                                navHostController.popBackStack()
                            }
                            is NavController.GoTo -> {
                                navHostController.navigate(controller.route)
                            }
                        }
                        mainViewModel.onNavigationHandled()
                    }
                }

                Scaffold(
//                    bottomBar = com.example.trackit.components.BottomAppBar()
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colors.background
                    ) {
                        NavHost(
                            navController = navHostController,
                            startDestination = Route.Login,
                        ) {

                            composable<Route.Home> {
                                HomeScreen()
                            }
                            composable<Route.Login> {
                                LoginScreen()
                                BackHandler(enabled = true) {
                                // Do not allow back navigation
                                }
                            }
                            composable<Route.UserInfo> {
                                UserInfoScreen()
                            }
                            composable<Route.Profile> {
                                ProfileScreen()
                            }
                            composable<Route.Wallet> {
                                WalletScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}
