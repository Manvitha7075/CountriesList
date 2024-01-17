package com.example.countrieslist.data.repository

import com.example.countrieslist.data.ApiState
import com.example.countrieslist.domain.mappers.CountriesMapper
import com.example.countrieslist.domain.model.CountriesEntity
import com.example.countrieslist.data.retrofit.RetrofitInstance

/**
 * Repository retrieves countries data from underlying data source.
 */
interface CountriesRepository {
    suspend fun getCountriesList():  ApiState<List<CountriesEntity>>
}

/**
 * Network Implementation of repository that retrieves countries data.
 */
class CountriesRepositoryImpl : CountriesRepository {

    override suspend fun getCountriesList(): ApiState<List<CountriesEntity>> {
        try {
            val request = RetrofitInstance.api.getCountriesList()
            if (request.isSuccessful) {
                val countryEntity = request.body()?.let { CountriesMapper.buildFrom(it) }
                return countryEntity?.let { ApiState.Success(countryEntity) }
                    ?: ApiState.Error("Failed to parse response body")
            }
            return ApiState.Error(request.message())
        } catch (e: Exception) {
            return ApiState.Error("An error occurred")
        }
    }

}
