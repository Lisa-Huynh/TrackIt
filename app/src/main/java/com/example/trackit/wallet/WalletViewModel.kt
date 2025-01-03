package com.example.trackit.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackit.data.models.Card
import com.example.trackit.data.models.Profile
import com.example.trackit.data.repositories.ProfileRepository
import com.example.trackit.data.repositories.WalletRepository
import com.example.trackit.util.BottomSheetUiState
import com.example.trackit.util.CardInfoUiState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val walletRepository: WalletRepository,
    private val profileRepository : ProfileRepository,
): ViewModel() {
    private val _bottomSheetState = MutableStateFlow(BottomSheetUiState.HIDDEN)
    val bottomSheetState = _bottomSheetState.asStateFlow()

    private val _cardInfoUiState = MutableStateFlow(CardInfoUiState("", ""))
    val cardInfoUiState = _cardInfoUiState.asStateFlow()

    fun onCardNameChange(cardName: String) {
        viewModelScope.launch {
            _cardInfoUiState.emit(CardInfoUiState(cardName, cardInfoUiState.value.initialExpense))
        }
    }

    fun onInitialExpenseChange(initialExpense: String) {
        viewModelScope.launch {
            _cardInfoUiState.emit(CardInfoUiState(cardInfoUiState.value.cardName, initialExpense))
        }
    }

    fun onAddCardClick() {
        viewModelScope.launch {
            _bottomSheetState.emit(BottomSheetUiState.VISIBLE)
        }
    }

    fun submitCardInfoClick() {
        viewModelScope.launch {
            val card = Card(
                cardName = _cardInfoUiState.value.cardName,
                totalExpense = _cardInfoUiState.value.initialExpense,
            )
            val accountId = FirebaseAuth.getInstance().currentUser?.uid!!
            val walletId = (profileRepository.getProfile(accountId) as? Profile.Loaded)?.walletId!!
            walletRepository.addCard(walletId, card)
            _bottomSheetState.emit(BottomSheetUiState.HIDDEN)
        }
    }

    fun dismissBottomSheetClick() {
        viewModelScope.launch {
            _bottomSheetState.emit(BottomSheetUiState.HIDDEN)
        }
    }
}