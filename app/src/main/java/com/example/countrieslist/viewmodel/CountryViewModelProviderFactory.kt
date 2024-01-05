package com.example.countrieslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countrieslist.repository.CountriesRepository
import com.example.countrieslist.repository.CountriesRepositoryImpl

class CountryViewModelProviderFactory(private val countriesRepository: CountriesRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountriesViewModel(countriesRepository = CountriesRepositoryImpl()) as T
    }
}