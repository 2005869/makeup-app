package com.fv2005869.makeupapp.network


import com.fv2005869.makeupapp.model.Product
import com.fv2005869.makeupapp.model.ProductItem
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET


private const val BASE_URL = "http://makeup-api.herokuapp.com"
private const val ENDPOINT = "api/v1/products.json"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }
        .asConverterFactory("application/json"
            .toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface MakeUpApiService {

    @GET(ENDPOINT)
    suspend fun getProducts():List<ProductItem>
//    suspend fun getProducts():List<Product>
}

