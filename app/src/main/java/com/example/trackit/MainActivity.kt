package com.example.trackit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trackit.navigation.ScreenNav
import com.example.trackit.ui.BottomAppBar
import com.example.trackit.ui.screens.HomeScreen
import com.example.trackit.ui.screens.LoginScreen
import com.example.trackit.ui.screens.WalletScreen
import com.example.trackit.ui.theme.AppTheme
import com.example.trackit.ui.viewmodels.LoginViewModel
import com.example.trackit.ui.viewmodels.WalletViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private lateinit var screenNav: ScreenNav
    private val walletViewModel: WalletViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                navController = rememberNavController()
                screenNav = ScreenNav(navController)

                val loggedIn by loginViewModel.currentUser.observeAsState()

                Scaffold(
                    bottomBar = {
                        if (loggedIn != null) {
                            BottomAppBar(navController = navController)
                            screenNav.homeScreen
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
                                route = "home screen"
                            ) {
                                HomeScreen(
                                    navToWallet = screenNav.walletScreen,
                                    walletViewModel = walletViewModel
                                )
                            }

                            composable(
                                route = "login screen"
                            ) {
                                LoginScreen(
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

@Composable
fun NavBarIcon(
    @DrawableRes drawableId: Int? = null,
    icon: ImageVector? = null,
    description: String
) {
    assert(drawableId != null || icon != null)
    if (drawableId != null) {
        Icon(
            painterResource(id = drawableId),
            contentDescription = description,
            modifier = Modifier
                .size(50.dp),
        )
    } else if (icon != null) {
        Icon(
            icon,
            contentDescription = description,
            modifier = Modifier
                .size(50.dp),
        )
    }
}