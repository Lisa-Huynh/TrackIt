package com.example.trackit.home

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

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val profile by viewModel.profileStream.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeScreenTopBar(
                name = (profile as? Profile.Loaded)?.firstName ?: "",
                date = viewModel.currentDate,
                onProfileIconClick = viewModel::onProfileIconClick,
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                HomeScreenContent()
            }
        }
    )
}

@Composable
fun HomeScreenTopBar(
    name: String,
    date: String,
    onProfileIconClick: () -> Unit,
) {
    val modifier = Modifier.fillMaxWidth()
    TopAppBar(
        modifier = modifier.wrapContentHeight(),
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        contentColor = MaterialTheme.colors.topAppBarContentColor,
        elevation = 0.dp,
        contentPadding = PaddingValues(25.dp, 15.dp),
        content = {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.SpaceBetween,
                content = {
                    Column {
                        Text(
                            text = "Hello, $name",
                            style = MaterialTheme.typography.h1
                        )
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = date,
                            style = MaterialTheme.typography.h4
                        )
                    }
                    IconButton(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(70.dp),
                        onClick = onProfileIconClick,
                        content = {
                            Icon(
                                imageVector = Icons.Rounded.AccountCircle,
                                contentDescription = "User Profile",
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                    )
                }
            )
        }
    )
}

@Composable
fun HomeScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreenTopBar("Lisa", "Nov 15. 2024") {}
        HomeScreenContent()
    }
}