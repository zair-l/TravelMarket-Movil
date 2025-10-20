package com.tecsup.travelmarket.data.model

import androidx.annotation.DrawableRes

data class Evento(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val fecha: String,
    val hora: String,
    val ubicacion: String,
    val urlEnlace: String,
    @DrawableRes val imagenId: Int,
    val categoria: String = "Eventos",
    val isFavorite: Boolean = false
)
