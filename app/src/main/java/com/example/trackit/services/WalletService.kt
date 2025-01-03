package com.example.trackit.services

import com.example.trackit.data.models.Card
import com.example.trackit.data.models.Wallet

interface WalletService {

    suspend fun createWallet(ownerId: String): Wallet.Loaded
    suspend fun getWallet(walletId: String): Wallet.Loaded
    suspend fun addCard(walletId: String, card: Card)
}