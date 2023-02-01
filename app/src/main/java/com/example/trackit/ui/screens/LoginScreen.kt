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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trackit.ui.theme.AppTheme
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
                    onEmailChange = loginViewModel::onEmailChange,
                    onPasswordChange = loginViewModel::onPasswordChange,
                    onLoginClick = loginViewModel::onLoginClick,
                    onSignUpClick = loginViewModel::onSignUpClick,
                )
            }
        }
    )
}

@Composable
fun LoginScreenContent(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
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
            onValueChange = onEmailChange)
        PasswordField(
            value = uiState.password,
            onValueChange = onPasswordChange)
        Button(
            onClick = onLoginClick,
            content = {
                Text(text = "Login")
            }
        )
        Button(
            onClick = onSignUpClick,
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

@Preview
@Composable
fun LoginScreenPreview() {
    AppTheme {
        LoginScreenContent(
            uiState = LoginUiState(email = "", password = ""),
            onEmailChange = { },
            onPasswordChange = { },
            onLoginClick = { },
            onSignUpClick = { }
        )
    }
}
