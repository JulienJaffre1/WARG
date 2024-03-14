package com.example.warg.data.repository

import android.util.Log
import com.example.warg.data.api.WargApi
import com.example.warg.data.core.Resource
import com.example.warg.domain.model.TokenDomain
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import io.ktor.http.isSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import org.json.JSONObject
import org.koin.core.component.KoinComponent

interface WargRepositoryInterface {
    suspend fun accountConnection(mail: String, password: String): Flow<Resource<TokenDomain>>
}

fun CoroutineScope.launchPeriodicAsync(
    repeatMillis: Long,
    action: () -> Unit
) = this.async {
    if (repeatMillis > 0) {
        while (isActive) {
            action()
            delay(repeatMillis)
        }
    } else {
        action()
    }
}

class WargRepository (
    private val wargApi: WargApi
) : KoinComponent,
        WargRepositoryInterface {
    override suspend fun accountConnection(mail: String, password: String): Flow<Resource<TokenDomain>> {
        return accountConnectionFlow(mail, password)
    }

    private suspend fun accountConnectionFlow(mail: String, password: String): Flow<Resource<TokenDomain>> = flow {
        emit(Resource.Loading())
        var result = wargApi.accountConnectionAPI(mail, password)
        if(result.token.equals("")) {
            Log.d("WARG", "WARG Successful")
            println("Successful response")

            emit(Resource.Success(result.toDomain()))
        }
        emit(Resource.Error(Throwable("Compte inexistant"),result.toDomain()))

    }
}