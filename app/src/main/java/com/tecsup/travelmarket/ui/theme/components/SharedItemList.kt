package com.tecsup.travelmarket.ui.theme.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tecsup.travelmarket.data.model.*
import com.tecsup.travelmarket.ui.theme.screens.LugarCard


fun LazyListScope.sharedItemList(
    items: List<Any>,
    onItemClick: (String) -> Unit
) {
    items(items) { item ->
        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
            when (item) {
                is Lugar -> LugarCard(
                    lugar = item,
                    onClick = { onItemClick(item.id) }
                )
                is Evento -> EventoCard(
                    evento = item,
                    onClick = { onItemClick(item.id) }
                )
                is Restaurante -> RestauranteCard(
                    restaurante = item,
                    onClick = { onItemClick(item.id) }
                )
                is Transporte -> TransporteCard(
                    transporte = item,
                    onClick = { onItemClick(item.id) }
                )
            }
        }
    }
}