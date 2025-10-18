package com.tecsup.travelmarket.data.repository

import com.tecsup.travelmarket.R
import com.tecsup.travelmarket.data.model.Lugar

object LugarRepository {

    private val sampleLugares = listOf(
        Lugar(
            id = 1,
            name = "Huaca Pucllana",
            description = "La Huaca Pucllana es un centro ceremonial precolombino de la cultura Lima. Destaca por su gran pirámide escalonada de adobe en medio de Miraflores.",
            imageRes = R.drawable.huaca_pucllana
        ),
        Lugar(
            id = 2,
            name = "Circuito Mágico del Agua",
            description = "Un impresionante conjunto de trece fuentes ornamentales, cibernéticas e interactivas donde el agua, la música, la luz y las imágenes se mezclan.",
            imageRes = R.drawable.circuito_magico
        ),
        Lugar(
            id = 3,
            name = "Distrito de Barranco",
            description = "Conocido por su ambiente bohemio, Barranco es famoso por el Puente de los Suspiros, sus coloridas casonas, galerías de arte y una vibrante vida nocturna.",
            imageRes = R.drawable.barranco
        ),
        Lugar(
            id = 4,
            name = "Plaza de Armas de Lima",
            description = "El corazón histórico de Lima, rodeada por la Catedral, el Palacio de Gobierno y el Palacio Municipal. Un lugar lleno de historia y arquitectura colonial.",
            imageRes = R.drawable.plaza_armas
        )
    )

    fun getLugares(): List<Lugar> {
        return sampleLugares
    }

    fun getLugarById(id: Int): Lugar? {
        return sampleLugares.find { it.id == id }
    }
}