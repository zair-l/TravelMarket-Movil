package com.tecsup.travelmarket.data.model

import androidx.annotation.DrawableRes

data class Lugar(
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes val imageRes: Int,
    val isFavorite: Boolean = false
)