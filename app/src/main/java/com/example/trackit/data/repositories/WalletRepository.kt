package com.example.trackit.data.repositories

import com.example.trackit.data.models.Card
import com.example.trackit.data.models.Wallet
import com.example.trackit.services.WalletService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalletRepository @Inject constructor(
    private val walletService: WalletService,
) {
    suspend fun getNewWallet(ownerId: String): Wallet.Loaded {
        return walletService.createWallet(ownerId)
    }

    suspend fun getWallet(walletId: String): Wallet.Loaded {
        return walletService.getWallet(walletId)
    }

    suspend fun addCard(walletId: String, card: Card) {
        walletService.addCard(walletId, card)
    }

    suspend fun getCards(walletId: String): List<Card> {
        return walletService.getCards(walletId)
    }
}