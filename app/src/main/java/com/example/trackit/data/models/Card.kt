package com.example.trackit.data.models

import android.accounts.Account

data class Card(
    val cardNumber: String,
    val expiryDate: String,
    val cardType: CardType,
    val accounts: List<Account> = emptyList(),
)

enum class CardType {
    CREDIT, DEBIT,
}