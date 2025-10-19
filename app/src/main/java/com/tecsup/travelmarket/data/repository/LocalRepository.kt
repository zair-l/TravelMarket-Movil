package com.tecsup.travelmarket.data.repository

import com.tecsup.travelmarket.R
import com.tecsup.travelmarket.data.model.Evento
import com.tecsup.travelmarket.data.model.Lugar
import com.tecsup.travelmarket.data.model.Restaurante
import com.tecsup.travelmarket.data.model.Transporte

class LocalRepository {

    private val sampleLugares = listOf(
        Lugar(
            id = "1",
            nombre = "Huaca Pucllana",
            descripcion = "La Huaca Pucllana es un centro ceremonial precolombino de la cultura Lima. Destaca por su gran pirámide escalonada de adobe en medio de Miraflores.",
            direccion = "Calle General Borgoño cuadra 8, Miraflores",
            horario = "9:00 AM - 5:00 PM",
            imagenId = R.drawable.huaca_pucllana
        ),
        Lugar(
            id = "2",
            nombre = "Circuito Mágico del Agua",
            descripcion = "Un impresionante conjunto de trece fuentes ornamentales, cibernéticas e interactivas donde el agua, la música, la luz y las imágenes se mezclan.",
            direccion = "Cercado de Lima",
            horario = "3:00 PM - 10:00 PM",
            imagenId = R.drawable.circuito_magico
        ),
        Lugar(
            id = "3",
            nombre = "Distrito de Barranco",
            descripcion = "Conocido por su ambiente bohemio, Barranco es famoso por el Puente de los Suspiros, sus coloridas casonas, galerías de arte y una vibrante vida nocturna.",
            direccion = "Barranco",
            horario = "24 horas",
            imagenId = R.drawable.barranco,
        ),
        Lugar(
            id = "4",
            nombre = "Plaza de Armas de Lima",
            descripcion = "El corazón histórico de Lima, rodeada por la Catedral, el Palacio de Gobierno y el Palacio Municipal. Un lugar lleno de historia y arquitectura colonial.",
            direccion = "Cercado de Lima",
            horario = "24 horas",
            imagenId = R.drawable.plaza_armas,
        )
    )

    private val eventosSimulados = listOf(
        Evento(
            id = "e1",
            nombre = "Ceremonia de Apertura",
            descripcion = "El evento inaugural de los Juegos Panamericanos con artistas y desfiles.",
            fecha = "25 de Octubre",
            hora = "7:00 PM",
            ubicacion = "Estadio Nacional",
            imagenId = R.drawable.estadio_nacional
        )
    )

    private val restaurantesSimulados = listOf(
        Restaurante(
            id = "r1",
            nombre = "Central Restaurante",
            descripcion = "Experiencia culinaria de primer nivel explorando ecosistemas peruanos.",
            direccion = "Av. Pedro de Osma 301, Barranco",
            tipoCocina = "Peruana de Altura",
            rangoPrecio = "$$$$",
            imagenId = R.drawable.central
        ),
        Restaurante(
            id = "r2",
            nombre = "Maido",
            descripcion = "Famoso restaurante de cocina Nikkei (fusión peruano-japonesa).",
            direccion = "Calle San Martin 399, Miraflores",
            tipoCocina = "Nikkei",
            rangoPrecio = "$$$$",
            imagenId = R.drawable.maido
        )
    )

    private val transportesSimulados = listOf(
        Transporte(
            id = "t1",
            nombre = "Metropolitano",
            descripcion = "Sistema de buses de tránsito rápido que conecta el norte y sur de Lima.",
            tipo = "Bus Público",
            cobertura = "Norte-Sur (vía Expresa)",
            imagenId = R.drawable.metropolitano
        )
    )

    fun getLugares(): List<Lugar> {
        return sampleLugares
    }
    fun getEventos(): List<Evento> {
        return eventosSimulados
    }
    fun getRestaurantes(): List<Restaurante> {
        return restaurantesSimulados
    }
    fun getTransportes(): List<Transporte> {
        return transportesSimulados
    }

    fun getLugarById(id: String): Lugar? {
        return sampleLugares.find { it.id == id }
    }

    fun getEventoById(id: String): Evento? {
        return eventosSimulados.find { it.id == id }
    }

    fun getRestauranteById(id: String): Restaurante? {
        return restaurantesSimulados.find { it.id == id }
    }

    fun getTransporteById(id: String): Transporte? {
        return transportesSimulados.find { it.id == id }
    }

    fun getAllItems(): List<Any> {
        return sampleLugares + eventosSimulados + restaurantesSimulados + transportesSimulados
    }
}