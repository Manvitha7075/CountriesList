package com.example.countrieslist.repository

import com.example.countrieslist.model.CountriesList
import com.example.countrieslist.retrofit.RetrofitInstance
import retrofit2.Response

/**
 * Repository retrieves countries data from underlying data source.
 */
interface CountriesRepository {
    suspend fun getCountriesList(): Response<CountriesList>
}

/**
 * Network Implementation of repository that retrieves countries data.
 */
class CountriesRepositoryImpl: CountriesRepository {

    override suspend fun getCountriesList() = RetrofitInstance.api.getCountriesList()

 }
