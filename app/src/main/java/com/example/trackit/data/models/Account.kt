package com.example.trackit.data.models

data class Account(
    val accountNumber: String,
    val accountType: AccountType,
)

enum class AccountType {
    CHEQUING, SAVINGS
}
