package com.example.trackit.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.IconButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.trackit.ui.theme.*
import com.example.trackit.ui.viewmodels.HomeViewModel
import com.example.trackit.ui.viewmodels.WalletViewModel
import com.example.trackit.util.Action
import com.example.trackit.util.ProfileUiState
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navToWallet: () -> Unit,
    homeViewModel: HomeViewModel
) {
    val uiState by homeViewModel.uiState
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeScreenTopBar(uiState.firstName)
        },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                HomeScreenContent(
                    uiState = uiState,
                    onFirstNameChange = homeViewModel::onFirstNameChange,
                    onLastNameChange = homeViewModel::onLastNameChange,
                    onSubmitInfoClick = homeViewModel::onSubmitInfoClick
                )
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
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
                horizontalArrangement = SpaceBetween,
                content = {
                    Column {
                        Text(
                            text = "Hello $name,",
                            style = MaterialTheme.typography.h1
                        )
                        Text(
                            text = LocalDate.now().format(DateTimeFormatter.ofPattern("MM dd")),
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
fun HomeScreenContent(
    uiState : ProfileUiState,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onSubmitInfoClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(25.dp, 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        FirstNameField(
            value = uiState.firstName,
            onValueChange = onFirstNameChange)

        LastNameField(
            value = uiState.lastName,
            onValueChange = onLastNameChange)

        Button(
            onClick = onSubmitInfoClick,
            contentPadding = PaddingValues(60.dp, 10.dp),
            content = {
                Text(
                    text = "Submit Information",
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        )

        Button(
            onClick = {
                //viewModel.handleDatabaseAction(Action.ADD)
            },
            contentPadding = PaddingValues(60.dp, 10.dp),
            content = {
                Text(
                    text = "Add a card",
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        )
    }
}

@Composable
fun FirstNameField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { androidx.compose.material.Text(text = "First Name: ") },
        placeholder = { androidx.compose.material.Text(text = "Enter your first name") },
        singleLine = true,
    )
}

@Composable
fun LastNameField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { androidx.compose.material.Text(text = "Last Name: ") },
        placeholder = { androidx.compose.material.Text(text = "Enter your last name") },
        singleLine = true,
    )
}



@Preview
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreenContent(
            uiState = ProfileUiState("Lisa", "Huynh"),
            onFirstNameChange = { },
            onLastNameChange = { },
            onSubmitInfoClick = { }
        )
    }
}