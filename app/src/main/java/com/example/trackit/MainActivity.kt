package com.example.trackit

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trackit.navigation.ScreenNav
import com.example.trackit.components.BottomAppBar
import com.example.trackit.data.models.Profile
import com.example.trackit.ui.screens.HomeScreen
import com.example.trackit.ui.screens.LoginScreen
import com.example.trackit.ui.screens.WalletScreen
import com.example.trackit.ui.screens.onboarding.UserInfoScreen
import com.example.trackit.ui.theme.AppTheme
import com.example.trackit.ui.viewmodels.HomeViewModel
import com.example.trackit.ui.viewmodels.LoginViewModel
import com.example.trackit.ui.viewmodels.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private lateinit var screenNav: ScreenNav
    private val onboardingViewModel: OnboardingViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            homeViewModel.currentProfile.collect { profile ->
                if (profile != Profile("", "", "")) screenNav.homeScreen.invoke()
            }
        }

        setContent {
            AppTheme {
                navController = rememberNavController()
                screenNav = ScreenNav(navController)

                val profile by homeViewModel.currentProfile.collectAsState(Profile("", "", ""))

                Scaffold(
                    bottomBar = {
                        if (profile != Profile("", "", "")) {
                            BottomAppBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colors.background
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "login screen"
                        ) {
                            composable(
                                route = "user info screen"
                            ) {
                                UserInfoScreen(
                                    onboardingViewModel = onboardingViewModel
                                )
                            }

                            composable(
                                route = "home screen"
                            ) {
                                HomeScreen(
                                    navToWallet = screenNav.walletScreen,
                                    homeViewModel = homeViewModel
                                )
                            }

                            composable(
                                route = "login screen"
                            ) {
                                LoginScreen(
                                    navToUserInfo = screenNav.userInfoScreen,
                                    loginViewModel = loginViewModel
                                )
                            }

                            composable(
                                route = "wallet screen"
                            ) {
                                WalletScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}


fun Boolean?.isTrue() = this == true