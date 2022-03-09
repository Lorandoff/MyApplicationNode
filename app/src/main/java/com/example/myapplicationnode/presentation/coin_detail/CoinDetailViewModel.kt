package com.example.myapplicationnode.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationnode.common.Constants.PARAM_COIN_ID
import com.example.myapplicationnode.common.Resource
import com.example.myapplicationnode.domain.model.CoinDetailState
import com.example.myapplicationnode.domain.model.CoinListState
import com.example.myapplicationnode.domain.use_case.get_coin.GetCoinUseCase
import com.example.myapplicationnode.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(CoinDetailState())
    val state : State<CoinDetailState>
    get() = _state
    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let {
            getCoins(it)
        }
    }
  //  }
    private fun getCoins(coinId : String){
        getCoinUseCase(coinId).onEach { resource ->
            when(resource){
                is Resource.Success -> {
                    _state.value = CoinDetailState(coins = resource.data )
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(error = resource.message ?: "Error")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}