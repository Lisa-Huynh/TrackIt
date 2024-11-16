package com.example.trackit.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.IconButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trackit.data.models.Profile
import com.example.trackit.ui.theme.*
import com.example.trackit.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val profile by homeViewModel.profileStream.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeScreenTopBar(
                name = (profile as? Profile.Loaded)?.firstName ?: ""
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                HomeScreenContent(homeViewModel::onLogoutButtonClick)
            }
        }
    )
}

@Composable
fun HomeScreenTopBar(
    name : String
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        contentColor = MaterialTheme.colors.topAppBarContentColor,
        elevation = 0.dp,
        contentPadding = PaddingValues(25.dp, 15.dp),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                content = {
                    Column {
                        Text(
                            text = "Hello, $name",
                            style = MaterialTheme.typography.h1
                        )
                        Text(
                            text = "MM dd",
                            style = MaterialTheme.typography.h4
                        )
                    }
                    IconButton(
                        onClick = { /*TODO*/ },
                        content = {
                            Icon(
                                Icons.Rounded.AccountCircle,
                                contentDescription = "User Profile",
                                modifier = Modifier
                                    .height(60.dp)
                                    .width(60.dp)
                            )
                        }
                    )
                }
            )
        }
    )
}

@Composable
fun HomeScreenContent(onLogoutButtonClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        Button(
            modifier = Modifier
                .wrapContentSize(),
            onClick = onLogoutButtonClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Logout",
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreenTopBar("Lisa")
        HomeScreenContent({})
    }
}