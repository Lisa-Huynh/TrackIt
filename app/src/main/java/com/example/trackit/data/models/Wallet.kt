package com.example.trackit.data.models

sealed class Wallet {
    data object Loading : Wallet()

    data class Loaded (
        val walletId: String,
        val ownerId: String,
        val cardIds: List<String>,
    ) : Wallet()

    companion object {
        fun fromMap(data: Map<String, Any>): Wallet.Loaded {
            return Wallet.Loaded(
                walletId = data["id"].toString(),
                ownerId = data["ownerId"].toString(),
                cardIds = data["cardIds"].toListOfStrings(),
            )
        }

        private fun ArrayList.toListOfStrings() {

        }
    }
}