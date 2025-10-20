package com.tecsup.travelmarket.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tecsup.travelmarket.data.repository.LocalRepository

class LugarViewModel : ViewModel() {

    private val repository = LocalRepository()

    private val _lugares = mutableStateOf(repository.getLugares())
    private val _eventos = mutableStateOf(repository.getEventos())

    val favoritos: State<List<Any>> = derivedStateOf {
        _lugares.value.filter { it.isFavorite } + _eventos.value.filter { it.isFavorite }
    }

    fun getItemById(itemId: String): Any? {
        return _lugares.value.find { it.id == itemId }
            ?: _eventos.value.find { it.id == itemId }
            ?: repository.getRestauranteById(itemId)
            ?: repository.getTransporteById(itemId)
    }

    fun toggleFavorite(itemId: String) {
        val lugarIndex = _lugares.value.indexOfFirst { it.id == itemId }
        if (lugarIndex != -1) {
            val lugar = _lugares.value[lugarIndex]
            val updatedLugar = lugar.copy(isFavorite = !lugar.isFavorite)
            val updatedList = _lugares.value.toMutableList()
            updatedList[lugarIndex] = updatedLugar
            _lugares.value = updatedList
            return
        }

        val eventoIndex = _eventos.value.indexOfFirst { it.id == itemId }
        if (eventoIndex != -1) {
            val evento = _eventos.value[eventoIndex]
            val updatedEvento = evento.copy(isFavorite = !evento.isFavorite)
            val updatedList = _eventos.value.toMutableList()
            updatedList[eventoIndex] = updatedEvento
            _eventos.value = updatedList
        }
    }
}