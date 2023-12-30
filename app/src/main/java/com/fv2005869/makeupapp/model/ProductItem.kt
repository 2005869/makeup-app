package com.fv2005869.makeupapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductItem(
    @SerialName("api_featured_image") val apiFeaturedImage: String,
    @SerialName("brand") val brand: String?,
    @SerialName("category") val category: String?,
    @SerialName("created_at") val createdAt: String,
    @SerialName("currency") val currency: String?,
    @SerialName("description") val description: String?,
    @SerialName("id") val id: Int,
    @SerialName("image_link") val imageLink: String,
    @SerialName("name") val name: String,
    @SerialName("price") val price: String?,
    @SerialName("price_sign") val priceSign: String?,
    @SerialName("product_api_url") val productApiUrl: String,
    @SerialName("product_colors") val productColors: List<ProductColor>,
    @SerialName("product_link") val productLink: String,
    @SerialName("product_type") val productType: String,
    @SerialName("rating") val rating: Double?,
    @SerialName("tag_list") val tagList: List<String>,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("website_link") val websiteLink: String
)