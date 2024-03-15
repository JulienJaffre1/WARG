package com.example.warg.data.repository

import android.util.Log
import com.example.warg.data.api.WargApi
import com.example.warg.data.core.Resource
import com.example.warg.domain.model.TokenDomain
import com.example.warg.domain.model.TokenEntityDomain
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
    suspend fun accountConnection(mail: String, password: String): Flow<Resource<TokenEntityDomain>>
    suspend fun accountCreation(name: String, password: String, mail: String): Flow<Resource<String>>
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
    override suspend fun accountConnection(mail: String, password: String): Flow<Resource<TokenEntityDomain>> {
        return accountConnectionFlow(mail, password)
    }

    private suspend fun accountConnectionFlow(mail: String, password: String): Flow<Resource<TokenEntityDomain>> = flow {
        emit(Resource.Loading())
        var result = wargApi.accountConnectionAPI(mail, password)

        Log.d("WARG", "Dans Flow : " + result.token)

        if (result != null) {
            Log.d("WARG", "WARG Successful")
            println("Successful response")

           //result = result!!



            emit(Resource.Success(result.toDomain()!!))
        }
        //Log.d("WARG", "WARG not successful")
        emit(Resource.Error(Throwable("Compte inexistant"),))
    }

    override suspend fun accountCreation(name: String, password: String, mail: String): Flow<Resource<String>> {
        return accountCrationFlow(name, password, mail)
    }

    private suspend fun accountCrationFlow(name: String, password: String, mail: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        var result = wargApi.accountCreationAPI(name, password, mail)
        Log.d("WARG", "Dans Flow creation : $result")

        if(result.status.value in 200..299) {
            Log.d("WARG", "WARG Successful")
            println("Successful response")

            emit(Resource.Success(result.bodyAsText()))
        }
        emit(Resource.Error(Throwable("Compte pas crée"),result.bodyAsText()))
    }
}