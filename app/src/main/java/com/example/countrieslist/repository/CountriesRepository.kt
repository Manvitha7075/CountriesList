package com.example.countrieslist.repository

import com.example.countrieslist.model.CountriesList
import com.example.countrieslist.retrofit.RetrofitInstance
import retrofit2.Response

interface CountriesRepository {
    suspend fun getCountriesList(): Response<CountriesList>
}

class CountriesRepositoryImpl: CountriesRepository {

    override suspend fun getCountriesList() = RetrofitInstance.api.getCountriesList()

 }
