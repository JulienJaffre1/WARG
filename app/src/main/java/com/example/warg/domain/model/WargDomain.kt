package com.example.warg.domain.model

import com.example.warg.data.api.TokenEntityDto
import kotlinx.serialization.Serializable

data class AccountDomain(
    val id: Int,
    val name: String,
    val password: String,
    val mail: String
)

data class AccountSettingsDomain(
    val id: Int,
    val steamUser: String,
    val gogUser: String
)

data class GamesDomain (
    val games: List<GameDomain>? = listOf()
)

data class GameDomain (
    val name: String,
    val imageUrl: String
)

data class TokenDomain(
    val token: TokenEntityDomain
)

data class TokenEntityDomain (
    val token: String
)

