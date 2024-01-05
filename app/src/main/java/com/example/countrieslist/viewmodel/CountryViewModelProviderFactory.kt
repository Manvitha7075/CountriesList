package com.example.countrieslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countrieslist.repository.CountriesRepository

class CountryViewModelProviderFactory(private val countriesRepository: CountriesRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountriesViewModel(countriesRepository) as T
    }
}