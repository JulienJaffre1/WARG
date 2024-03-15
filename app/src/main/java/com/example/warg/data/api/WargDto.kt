package com.example.warg.data.api

import kotlinx.serialization.Serializable

@Serializable
data class AccountDto(
    val id: Int,
    val name: String,
    val password: String,
    val mail: String
)

@Serializable
data class AccountSettingsDto(
    val id: Int,
    val steamUser: String,
    val gogUser: String
)

@Serializable
data class GamesDto (
    val games: List<GameDto>? = listOf()
)

@Serializable
data class GameDto (
    val name: String,
    val imageUrl: String
)

@Serializable
data class TokenDto (
    val token: TokenEntityDto
)

@Serializable
data class TokenEntityDto(
    val token: String
)
