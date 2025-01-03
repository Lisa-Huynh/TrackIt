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

    override suspend fun getCards(walletId: String): List<Card> {
        val cardIds = this.getWallet(walletId).cardIds
        return mapCardIdsToListOfCards(cardIds)
    }

    override suspend fun getCard(cardId: String): Card {
        val cardData = Firebase.firestore.collection("Cards").document(cardId).get().await().data!!
        return mapToCard(cardData)
    }

    private suspend fun mapCardIdsToListOfCards(cardIds: List<String>): List<Card> {
        return cardIds.map { cardId ->
            this.getCard(cardId)
        }
    }

    private fun mapToCard(data: Map<String, Any>): Card {
        return Card(
            cardName = data["cardName"].toString(),
            totalExpense = data["totalExpense"].toString(),
        )
    }
}