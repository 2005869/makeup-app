package com.fv2005869.makeupapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductColor(
    @SerialName("colour_name") val colourName: String?,
    @SerialName("hex_value") val hexValue: String?
)
