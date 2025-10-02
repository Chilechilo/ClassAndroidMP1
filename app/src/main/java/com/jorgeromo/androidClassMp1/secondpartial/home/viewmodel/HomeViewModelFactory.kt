package com.jorgeromo.androidClassMp1.secondpartial.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jorgeromo.androidClassMp1.secondpartial.home.model.repository.HomeRepository

class HomeViewModelFactory(
    private val repo: HomeRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
