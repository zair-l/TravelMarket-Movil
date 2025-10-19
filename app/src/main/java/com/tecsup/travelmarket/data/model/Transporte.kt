package com.tecsup.travelmarket.data.model

import androidx.annotation.DrawableRes

data class Transporte(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val tipo: String,
    val cobertura: String,
    @DrawableRes val imagenId: Int,
    val categoria: String = "Transporte"
)
