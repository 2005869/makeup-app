package com.fv2005869.makeupapp.data

import com.fv2005869.makeupapp.model.Product
import com.fv2005869.makeupapp.model.ProductItem
import com.fv2005869.makeupapp.network.MakeUpApiService

interface MakeUpRepository {
    suspend fun getMakeUpProducts(): List<ProductItem>
//    suspend fun getMakeUpProducts(): List<Product>
}

class NetworkMakeUpRepository(private val makeupApiService:MakeUpApiService):MakeUpRepository{
    override suspend fun getMakeUpProducts(): List<ProductItem> {
//    override suspend fun getMakeUpProducts(): List<Product> {
        return makeupApiService.getProducts()
    }
}