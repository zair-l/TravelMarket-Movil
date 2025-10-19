package com.tecsup.travelmarket.data.model

import androidx.annotation.DrawableRes

data class Lugar(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val direccion: String,
    val horario: String,
    val categoria: String = "Lugares",
    @DrawableRes val imagenId: Int,
    val isFavorite: Boolean = false
)