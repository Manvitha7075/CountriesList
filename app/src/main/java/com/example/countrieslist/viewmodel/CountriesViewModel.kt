package com.example.countrieslist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrieslist.model.CountriesList
import com.example.countrieslist.repository.CountriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response

class CountriesViewModel (val countriesRepository: CountriesRepository): ViewModel() {
    val countryPage: MutableLiveData<ApiState<CountriesList>> = MutableLiveData()

    init {
        getCountryData()
    }

    /**
     * * Gets countries data from the countires API
     */
    private fun getCountryData() = viewModelScope.launch(Dispatchers.IO) {
        countryPage.postValue(ApiState.Loading())
        val response = countriesRepository.getCountriesList()
        delay(1000) // just to show progress bar in the ui
        countryPage.postValue(handleResponse(response))
    }

    private fun handleResponse(response: Response<CountriesList>): ApiState<CountriesList> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return ApiState.Success(resultResponse)
            }
        }
        return ApiState.Error(response.message(), response.body())
    }
}