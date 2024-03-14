package com.example.warg.data.repository

import com.example.warg.data.api.AccountDto
import com.example.warg.data.api.AccountSettingsDto
import com.example.warg.data.api.GameDto
import com.example.warg.data.api.GamesDto
import com.example.warg.data.api.TokenDto
import com.example.warg.domain.model.AccountDomain
import com.example.warg.domain.model.AccountSettingsDomain
import com.example.warg.domain.model.GameDomain
import com.example.warg.domain.model.GamesDomain
import com.example.warg.domain.model.TokenDomain

fun AccountDto.toDomain() = AccountDomain(
    id = this.id,
    name = this.name,
    password = this.password,
    mail = this.mail
)

fun AccountSettingsDto.toDomain() = AccountSettingsDomain(
    id = this.id,
    steamUser = this.steamUser,
    gogUser = this.gogUser
)

fun GamesDto.toDomain() = GamesDomain(
    games = this.games?.map { it.toDomain() }
)

fun GameDto.toDomain() = GameDomain(
    name = this.name,
    imageUrl = this.imageUrl
)

fun TokenDto.toDomain() = TokenDomain(
    token = this.token
)