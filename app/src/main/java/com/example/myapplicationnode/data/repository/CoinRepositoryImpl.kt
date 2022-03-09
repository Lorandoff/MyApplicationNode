package com.example.myapplicationnode.data.repository

import com.example.myapplicationnode.data.remote.CoinPaprikaApi
import com.example.myapplicationnode.data.remote.dto.CoinDetailDto
import com.example.myapplicationnode.data.remote.dto.CoinDto
import com.example.myapplicationnode.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api : CoinPaprikaApi
) : CoinRepository{
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinbyId(coinId: String): CoinDetailDto {
      return api.getCoinById(coinId = coinId)
    }
}