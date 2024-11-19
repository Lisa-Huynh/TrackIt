package com.example.trackit.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        LogoutButton(onLogoutButtonClick = viewModel::onLogoutButtonClick)
    }
}

@Composable
private fun LogoutButton(
    onLogoutButtonClick: () -> Unit,
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
