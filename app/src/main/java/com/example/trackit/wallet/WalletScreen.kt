package com.example.trackit.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trackit.ui.theme.AppTheme
import com.example.trackit.ui.theme.topAppBarBackgroundColor
import com.example.trackit.ui.theme.topAppBarContentColor

@Composable
fun WalletScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        WalletScreenTopBar()
        WalletScreenContent()
    }
}

@Composable
fun WalletScreenContent(
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
                .clip(shape = RoundedCornerShape(15.dp))
                .background(MaterialTheme.colors.secondary)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 15.dp),
                text = "Add a card",
                style = MaterialTheme.typography.h4
            )
        }
    }
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