package com.example.countrieslist.data.repository

import com.example.countrieslist.domain.mappers.CountriesMapper
import com.example.countrieslist.domain.model.CountriesEntity
import com.example.countrieslist.data.retrofit.RetrofitInstance

/**
 * Repository retrieves countries data from underlying data source.
 */
interface CountriesRepository {
    suspend fun getCountriesList(): List<CountriesEntity>?
}

/**
 * Network Implementation of repository that retrieves countries data.
 */
class CountriesRepositoryImpl: CountriesRepository {

    override suspend fun getCountriesList(): List<CountriesEntity>? {
        val request =  RetrofitInstance.api.getCountriesList()
        if(request.isSuccessful){
            return request.body()?.let { CountriesMapper.buildFrom(it) }
        }
        return null
    }

 }
