package com.example.trackit.data.models

sealed class Wallet {
    data object Loading : Wallet()

    data class Loaded (
        val accounts: List<Account>,
    ) : Wallet()
}