package com.example.trackit.services

import com.example.trackit.data.models.Card
import com.example.trackit.data.models.Wallet
import com.example.trackit.data.models.Wallet.Companion.fromMap
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class WalletServiceImpl @Inject constructor(
): WalletService {
    override suspend fun createWallet(ownerId: String): Wallet.Loaded {
        val walletId = Firebase.firestore.collection("Wallets").document().id
        val wallet = hashMapOf(
            "walletId" to walletId,
            "ownerId" to ownerId,
            "cardIds" to arrayListOf<String>(),
        )
        Firebase.firestore.collection("Wallets").document(walletId).set(wallet).await()
        return Wallet.Loaded(
            ownerId = ownerId,
            walletId = walletId,
            cardIds = emptyList(),
        )
    }

    override suspend fun getWallet(walletId: String): Wallet.Loaded {
        val wallet = Firebase.firestore.collection("Wallets").document(walletId).get().await().data!!
        return fromMap(wallet)
    }

    override suspend fun addCard(walletId: String, card: Card) {
        val cardId = Firebase.firestore.collection("Cards").document().id
        val cardData = hashMapOf(
            "cardId" to cardId,
            "cardName" to card.cardName,
            "transactionsId" to arrayListOf<String>(),
            "totalExpense" to card.totalExpense,
        )
        Firebase.firestore.collection("Wallets").document(walletId).update("cardIds", FieldValue.arrayUnion(cardId)).await()
        Firebase.firestore.collection("Cards").document(cardId).set(cardData).await()
    }
}