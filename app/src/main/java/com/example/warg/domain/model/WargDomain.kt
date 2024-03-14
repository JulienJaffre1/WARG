package com.example.warg.domain.model

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

data class TokenDomain (
    val token: String
)

