package com.example.trackit.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trackit.ui.theme.AppTheme
import com.example.trackit.ui.theme.topAppBarBackgroundColor
import com.example.trackit.ui.theme.topAppBarContentColor

@Composable
fun WalletScreen() {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            WalletScreenTopBar()
        },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {

            }

        }
    )
}

@Composable
fun WalletScreenTopBar() {
    TopAppBar(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        contentColor = MaterialTheme.colors.topAppBarContentColor,
        elevation = 0.dp,
        contentPadding = PaddingValues(start = 25.dp, end = 25.dp, top = 15.dp),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                content = {
                    Text(
                        text = "Wallet",
                        style = MaterialTheme.typography.h1
                    )
                }
            )
        }
    )
}

@Preview
@Composable
fun WalletScreenPreview() {
    AppTheme {
        WalletScreen()
    }
}