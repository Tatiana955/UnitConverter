package com.example.unitconverter.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.unitconverter.domain.repository.Repository

@Suppress("UNCHECKED_CAST")
class AppViewModelFactory(
    private val repository: Repository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AppViewModel(repository) as T
    }
}