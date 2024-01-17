package com.example.countrieslist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrieslist.data.ApiState
import com.example.countrieslist.domain.usecase.GetCountriesUseCase
import com.example.countrieslist.domain.model.CountriesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CountriesViewModel (val countriesUseCase: GetCountriesUseCase): ViewModel() {
    val countryLiveData: MutableLiveData<ApiState<List<CountriesEntity>>> = MutableLiveData()

    init {
        getCountryData()
    }

    /**
     * * Gets countries data from the countires API
     */
    private fun getCountryData() = viewModelScope.launch(Dispatchers.IO) {
        countryLiveData.postValue(ApiState.Loading())
        val response = countriesUseCase.invoke()
        delay(1000) // just to show progress bar in the ui
        countryLiveData.postValue(handleResponse(response))
    }

    private fun handleResponse(response: List<CountriesEntity>?): ApiState<List<CountriesEntity>> {
            response?.let { resultResponse ->
                return ApiState.Success(resultResponse)
            }
        return ApiState.Error()
    }
}