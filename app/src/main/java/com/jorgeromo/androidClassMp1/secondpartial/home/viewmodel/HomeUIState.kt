package com.jorgeromo.androidClassMp1.secondpartial.home.viewmodel

import com.jorgeromo.androidClassMp1.secondpartial.home.model.dto.Category

data class HomeUiState(
    val categorias: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)