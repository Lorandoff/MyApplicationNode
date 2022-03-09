package com.example.myapplicationnode.domain.model

data class CoinDetailState(
    val isLoading : Boolean = false,
    val coins : CoinDetail? = null,
    val error : String = ""

) {
}