package com.example.countrieslist.repository

import com.example.countrieslist.retrofit.RetrofitInstance

class CountriesRepository () {

        suspend fun getCountriesList() = RetrofitInstance.api.getCountriesList()


    }
