package com.example.countrieslist.service

import com.example.countrieslist.model.CountriesList
import retrofit2.Response
import retrofit2.http.GET

/**
 * A public interface that exposes the [getCountriesList] method
 */
interface CountriesListService {
    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountriesList(): Response<CountriesList>
}