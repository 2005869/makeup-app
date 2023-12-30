package com.fv2005869.makeupapp.data

import com.fv2005869.makeupapp.network.MakeUpApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val makeupRepository: MakeUpRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "http://makeup-api.herokuapp.com"
    private val ENDPOINT = "api/v1/products.json"

    private val retrofit = Retrofit
        .Builder()
        .addConverterFactory(Json {
            coerceInputValues = true
            ignoreUnknownKeys = true
        }
            .asConverterFactory(
                "application/json"
                    .toMediaType()
            ))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: MakeUpApiService by lazy {
        retrofit.create(MakeUpApiService::class.java)
    }

    override val makeupRepository: MakeUpRepository by lazy {
        NetworkMakeUpRepository(retrofitService)
    }
}