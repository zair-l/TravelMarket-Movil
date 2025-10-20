package com.tecsup.travelmarket.data.model

import androidx.annotation.DrawableRes

data class Lugar(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val direccion: String,
    val horario: String,
    val categoria: String = "Lugares",
    val urlEnlace: String,
    @DrawableRes val imagenId: Int,
    val isFavorite: Boolean = false
)