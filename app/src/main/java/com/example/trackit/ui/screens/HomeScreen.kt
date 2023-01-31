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
import com.example.trackit.ui.viewmodels.WalletViewModel
import com.example.trackit.util.Action
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun HomeScreen(
    navToWallet: () -> Unit,
    walletViewModel: WalletViewModel
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeScreenTopBar()
        },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding)
            )
            {
                HomeScreenContent(viewModel = walletViewModel)
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreenTopBar() {
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
                            text = "Hello!",
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
                            Icon(Icons.Rounded.AccountCircle,
                                contentDescription = "User Profile",
                                modifier = Modifier
                                    .height(60.dp)
                                    .width(60.dp),)
                        }
                    )
                }
            )
        }
    )
}


@Composable
fun HomeScreenContent(viewModel: WalletViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(25.dp, 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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


//@Preview
//@Composable
//fun HomeScreenPreview() {
//    AppTheme {
//        HomeScreen {}
//    }
//}