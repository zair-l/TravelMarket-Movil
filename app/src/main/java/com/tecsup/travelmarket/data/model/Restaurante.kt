package com.tecsup.travelmarket.data.model

import androidx.annotation.DrawableRes

data class Restaurante(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val direccion: String,
    val tipoCocina: String,
    val rangoPrecio: String,
    @DrawableRes val imagenId: Int,
    val categoria: String = "Gastronomía"
)
