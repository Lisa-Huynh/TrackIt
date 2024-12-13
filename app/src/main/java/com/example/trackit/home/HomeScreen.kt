package com.example.trackit.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trackit.data.models.Profile
import com.example.trackit.data.models.Wallet
import com.example.trackit.ui.theme.*

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val profile by viewModel.profileStream.collectAsState()
    val wallet by viewModel.walletStream.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeScreenTopBar(
            name = (profile as? Profile.Loaded)?.firstName ?: "",
            date = viewModel.currentDate,
            onProfileIconClick = viewModel::onProfileIconClick,
        )
        HomeScreenContent(
            isWalletEmpty = (wallet as? Wallet.Loaded)?.cards?.isEmpty() ?: true,
            onWalletClick = viewModel::onWalletClick,
        )
    }
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
fun HomeScreenContent(
    isWalletEmpty: Boolean,
    onWalletClick: () -> Unit,
) {
    val modifier = Modifier.fillMaxWidth()
    Column(
        modifier = modifier.padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        WalletSection(
            isWalletEmpty,
            modifier,
            onWalletClick,
        )
    }
}

@Composable
fun WalletSection(
    isWalletEmpty: Boolean,
    modifier: Modifier,
    onWalletClick: () -> Unit,
) {
    if (isWalletEmpty) {
        Box(
            modifier = modifier
                .padding(horizontal = 30.dp)
                .clickable { onWalletClick.invoke() }
                .height(200.dp)
                .clip(shape = RoundedCornerShape(25.dp))
                .background(color = MaterialTheme.colors.onError)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Wallet is empty! Click to add a card",
                style = TextStyle.Default,
            )
        }
    } else {
        Box(
            modifier = modifier
                .padding(horizontal = 50.dp)
                .clickable { onWalletClick.invoke() }
        ) {
            Box(
                modifier = modifier
                    .padding(horizontal = 15.dp)
                    .align(Alignment.TopCenter)
                    .height(50.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = MaterialTheme.colors.primary)
            )
            Box(
                modifier = modifier
                    .padding(start = 10.dp, end = 10.dp, top = 7.dp)
                    .align(Alignment.TopCenter)
                    .height(50.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = MaterialTheme.colors.secondary)
            )
            Box(
                modifier = modifier
                    .padding(top = 20.dp)
                    .align(Alignment.BottomCenter)
                    .height(170.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(color = MaterialTheme.colors.surface),
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen()
    }
}