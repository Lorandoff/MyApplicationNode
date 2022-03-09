package com.example.myapplicationnode.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationnode.common.Constants
import com.example.myapplicationnode.common.Resource
import com.example.myapplicationnode.domain.model.CoinListState
import com.example.myapplicationnode.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(CoinListState())
    val state : State<CoinListState>
    get() = _state
    init {
        getCoins()
    }
    fun saveState(coinId: String){
        savedStateHandle.set(Constants.PARAM_COIN_ID, coinId)
    }
    private fun getCoins(){
        getCoinsUseCase().onEach { resource ->
            when(resource){
                is Resource.Success -> {
                    _state.value = CoinListState(coins = resource.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = resource.message ?: "Error")
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}