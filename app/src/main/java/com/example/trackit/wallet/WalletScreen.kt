package com.example.trackit.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trackit.ui.theme.AppTheme
import com.example.trackit.ui.theme.Shapes
import com.example.trackit.ui.theme.topAppBarBackgroundColor
import com.example.trackit.ui.theme.topAppBarContentColor
import com.example.trackit.util.BottomSheetUiState
import com.example.trackit.util.CardInfoUiState

@Composable
fun WalletScreen(
    viewModel: WalletViewModel = hiltViewModel(),
) {
    val bottomSheetState by viewModel.bottomSheetState.collectAsStateWithLifecycle()
    val cardInfoUiState by viewModel.cardInfoUiState.collectAsStateWithLifecycle()

    when (bottomSheetState) {
        BottomSheetUiState.VISIBLE ->
            AddCardBottomSheet(
                cardInfoUiState = cardInfoUiState,
                onCardNameChange = viewModel::onCardNameChange,
                onInitialExpenseChange = viewModel::onInitialExpenseChange,
                onSubmitClick = viewModel::submitCardInfoClick,
                onDismiss = viewModel::dismissBottomSheetClick,
            )
        else -> {}
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        WalletScreenTopBar()
        WalletScreenContent(
            onAddCardClick = viewModel::onAddCardClick,
        )
    }
}

@Composable
fun WalletScreenContent(
    onAddCardClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .clickable { onAddCardClick.invoke() }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardBottomSheet(
    cardInfoUiState: CardInfoUiState,
    onCardNameChange: (String) -> Unit,
    onInitialExpenseChange: (String) -> Unit,
    onSubmitClick: () -> Unit,
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(
        shape = Shapes.medium,
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            OutlinedTextField(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                value = cardInfoUiState.cardName,
                onValueChange = { onCardNameChange(it) },
                label = { Text(text = "Card name") },
                placeholder = { Text(text = "Enter the name of the card") },
                singleLine = true,
            )
            OutlinedTextField(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                value = cardInfoUiState.initialExpense,
                onValueChange = { onInitialExpenseChange(it) },
                label = { Text(text = "Initial expense") },
                placeholder = { Text(text = "Enter the total expense on the card") },
                singleLine = true,
            )
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 20.dp),
                onClick = onSubmitClick,
                contentPadding = PaddingValues(60.dp, 10.dp),
                content = {
                    Text(
                        text = "Add card",
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                },
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

@Preview
@Composable
fun AddCardBottomSheetPreview() {
    AppTheme {
        AddCardBottomSheet(
            cardInfoUiState = CardInfoUiState("", ""),
            onCardNameChange = {},
            onInitialExpenseChange = {},
            onSubmitClick = {},
            onDismiss = {},
        )
    }
}