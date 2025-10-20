package com.tecsup.travelmarket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecsup.travelmarket.data.repository.LocalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

data class SearchUiState(
    val searchQuery: String = "",
    val selectedCategory: String = "Todos",
    val results: List<Any> = emptyList()
)

class SearchViewModel: ViewModel() {
    private val repository = LocalRepository()

    private val _searchQuery = MutableStateFlow("")
    private val _selectedCategory = MutableStateFlow("Todos")
    private val _uiState = MutableStateFlow(SearchUiState())

    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(_searchQuery, _selectedCategory) { query, category ->
                val filteredList = repository.getFilteredItems(query, category)
                SearchUiState(
                    searchQuery = query,
                    selectedCategory = category,
                    results = filteredList
                )
            }.collect { newState ->
                _uiState.value = newState
            }

        }
    }

    fun onQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun onCategorySelected(category: String) {
        _selectedCategory.value = category
    }
}
