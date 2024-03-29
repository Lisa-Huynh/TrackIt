package com.example.trackit.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackit.data.models.Item
import com.example.trackit.data.repositories.ItemRepository
import com.example.trackit.util.Action
import com.example.trackit.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    //private val repository: ItemRepository,
): ViewModel() {

    /*val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    val id: MutableState<Int> = mutableStateOf(0)
    val accessToken: MutableState<String> = mutableStateOf("")

    private val _allItems = MutableStateFlow<RequestState<List<Item>>>(RequestState.Idle)
    val allItems: StateFlow<RequestState<List<Item>>> get() = _allItems

    fun getAllCards() {
        _allItems.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getAllItems.collect {
                    _allItems.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allItems.value = RequestState.Error(e)
        }

    }

    private fun addAccount() {
        // create new link token
        // initialize LINK
        // get public token from LINK
        // exchange public token with access token
        // save access token value
        viewModelScope.launch(Dispatchers.IO) {
            val item = Item(
                id = id.value,
                accessToken = accessToken.value
            )
            repository.addAccount(item)
        }
    }

    private fun deleteAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            val item = Item(
                id = id.value,
                accessToken = accessToken.value
            )
            repository.deleteAccount(item)
        }
    }

    fun handleDatabaseAction(action: Action) {
        when (action) {
            Action.ADD -> addAccount()
            Action.DELETE -> deleteAccount()
            else -> {}
        }
    }*/
}