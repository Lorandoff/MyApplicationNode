package com.example.myapplicationnode.domain.repository

import com.example.myapplicationnode.data.remote.dto.CoinDetailDto
import com.example.myapplicationnode.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins() : List<CoinDto>

    suspend fun getCoinbyId(coinId : String) : CoinDetailDto
}