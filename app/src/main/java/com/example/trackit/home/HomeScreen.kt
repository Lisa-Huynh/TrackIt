package com.example.trackit.home

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
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
import androidx.compose.material3.Shapes
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trackit.data.models.Profile
import com.example.trackit.ui.theme.*
import kotlinx.serialization.json.JsonNull.content

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val profile by viewModel.profileStream.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeScreenTopBar(
            name = (profile as? Profile.Loaded)?.firstName ?: "",
            date = viewModel.currentDate,
            onProfileIconClick = viewModel::onProfileIconClick,
        )
        HomeScreenContent()
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
fun HomeScreenContent() {
    val modifier = Modifier.fillMaxWidth()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        WalletSection(modifier)
    }
}

@Composable
fun WalletSection(modifier: Modifier) {
    Box(
        modifier = modifier
            .padding(horizontal = 50.dp)
    ) {
        Box(modifier = modifier.padding(horizontal = 10.dp)
            .align(Alignment.TopCenter)
            .height(50.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = MaterialTheme.colors.primary)
        )
        Box(modifier = modifier.padding(start = 10.dp, end = 10.dp, top = 7.dp)
            .align(Alignment.TopCenter)
            .height(50.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = MaterialTheme.colors.secondary)
        )
        Box(modifier = modifier.padding(top = 20.dp)
            .align(Alignment.BottomCenter)
            .height(170.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = MaterialTheme.colors.primaryVariant),
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen()
    }
}