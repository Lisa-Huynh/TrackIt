package com.example.trackit.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.trackit.ui.viewmodels.LoginViewModel
import com.example.trackit.util.LoginUiState

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel
) {
    val uiState by loginViewModel.uiState
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding)
            )
            {
                LoginScreenContent(
                    uiState = uiState,
                    viewModel = loginViewModel
                )
            }
        }
    )
}

@Composable
fun LoginScreenContent(
    uiState: LoginUiState,
    viewModel: LoginViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(25.dp, 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        EmailField(
            value = uiState.email,
            onValueChange = viewModel::onEmailChange)
        PasswordField(
            value = uiState.password,
            onValueChange = viewModel::onPasswordChange)
        Button(
            onClick = { viewModel.onLoginClick(uiState.email, uiState.password) },
            content = {
                Text(text = "Login")
            }
        )
        Button(
            onClick = { viewModel.onSignUpClick(uiState.email, uiState.password) },
            content = {
                Text(text = "Sign Up")
            }
        )
    }
}

@Composable
fun EmailField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = "E-mail: ") },
        placeholder = { Text(text = "Enter your e-mail") },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Email,
                contentDescription = "",
                modifier = Modifier
                .size(30.dp)
            )
        }
    )
}

@Composable
fun PasswordField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = "Password: ") },
        placeholder = { Text(text = "Enter your password") },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Lock,
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
            )
        },
        visualTransformation = {
            TransformedText(
                AnnotatedString("*".repeat(value.length)),
                OffsetMapping.Identity
            )
        }
    )
}

//@Preview
//@Composable
//fun LoginScreenPreview() {
//    AppTheme {
//        LoginScreen()
//    }
//}