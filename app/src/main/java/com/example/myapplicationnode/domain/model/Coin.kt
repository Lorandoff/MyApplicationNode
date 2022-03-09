package com.example.myapplicationnode.domain.model

import com.example.myapplicationnode.data.remote.dto.CoinDto
import com.google.gson.annotations.SerializedName

data class Coin(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String
)

