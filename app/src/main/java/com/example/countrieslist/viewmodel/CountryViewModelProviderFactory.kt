package com.example.countrieslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countrieslist.data.repository.CountriesRepositoryImpl
import com.example.countrieslist.domain.usecase.GetCountriesUseCaseImpl

class CountryViewModelProviderFactory() : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountriesViewModel( countriesUseCase = GetCountriesUseCaseImpl(CountriesRepositoryImpl())) as T
    }
}