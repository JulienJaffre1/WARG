package com.example.warg.data.api

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import org.koin.core.component.KoinComponent

class Login (
    var mail: String,
    var password: String
)

class WargApi (
    private val client: HttpClient,
    private var baseUrl: String = "http://52.47.150.41:8080/api/v1/",
    ) : KoinComponent {

    @OptIn(InternalAPI::class)
    suspend fun accountConnectionAPI(mail: String, password: String): TokenDto {
        Log.d("WARG", "WARG API")
        return client.post("http://52.47.150.41:8080/api/v1/login/login") {
            headers.append(HttpHeaders.ContentType, "application/json")
            contentType(ContentType.Application.Json)
            setBody("BITE")
        }.body<TokenDto>()
    }

        /*suspend fun fetchWeather(city: String) : WeatherResultDto {
            return client.get("$baseUrl") {
                url {
                    parameters.append("q", "$city, FR")
                    parameters.append("APPID", apiKey)
                    parameters.append("units", "metric")
                }
            }.body<WeatherResultDto>()
        }

        suspend fun fetchWeather(lat: Double, lon : Double) : WeatherResultDto {
            return client.get("$baseUrl") {
                url {
                    parameters.append("lat", "$lat")
                    parameters.append("lon", "$lon")
                    parameters.append("APPID", apiKey)
                    parameters.append("units", "metric")
                }
            }.body<WeatherResultDto>()
        }*/
    }