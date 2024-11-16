package com.example.trackit.onboarding.userinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trackit.ui.theme.AppTheme
import com.example.trackit.util.ProfileUiState

@Composable
fun UserInfoScreen (
    userInfoViewModel: UserInfoViewModel = hiltViewModel(),
) {
    val uiState by userInfoViewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                UserInfoScreenContent(
                    uiState = uiState,
                    onFirstNameChange = userInfoViewModel::onFirstNameChange,
                    onLastNameChange = userInfoViewModel::onLastNameChange,
                    onSubmitInfoClick = userInfoViewModel::onSubmitInfoClick,
                )
            }
        }
    )
}




@Composable
fun UserInfoScreenContent(
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
            onValueChange = onFirstNameChange,
        )

        LastNameField(
            value = uiState.lastName,
            onValueChange = onLastNameChange,
        )

        Button(
            modifier = Modifier.padding(top = 20.dp),
            onClick = onSubmitInfoClick,
            contentPadding = PaddingValues(60.dp, 10.dp),
            content = {
                Text(
                    text = "Submit Information",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            },
        )

//        Button(
//            onClick = {
//                //viewModel.handleDatabaseAction(Action.ADD)
//            },
//            contentPadding = PaddingValues(60.dp, 10.dp),
//            content = {
//                Text(
//                    text = "Add a card",
//                    style = MaterialTheme.typography.h1,
//                    textAlign = TextAlign.Center,
//                    color = Color.White
//                )
//            }
//        )
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
        label = { Text(text = "First Name") },
        placeholder = { Text(text = "Enter your first name") },
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
        label = { Text(text = "Last Name") },
        placeholder = { Text(text = "Enter your last name") },
        singleLine = true,
    )
}



@Preview
@Composable
fun UserInfoScreenPreview() {
    AppTheme {
        UserInfoScreenContent(
            uiState = ProfileUiState("Lisa", "Huynh"),
            onFirstNameChange = {},
            onLastNameChange = {},
            onSubmitInfoClick = {}
        )
    }
}
