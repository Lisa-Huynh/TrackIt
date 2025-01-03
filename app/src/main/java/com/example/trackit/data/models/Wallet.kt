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
                walletId = data["walletId"].toString(),
                ownerId = data["ownerId"].toString(),
                cardIds = mapToListOfStrings(data["cardIds"]) ?: emptyList(),
            )
        }

        private fun mapToListOfStrings(data: Any?): List<String>? {
            return (data as? ArrayList<String>)?.toList()
        }
    }
}