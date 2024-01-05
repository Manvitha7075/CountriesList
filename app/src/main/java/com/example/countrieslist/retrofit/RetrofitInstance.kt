package com.example.countrieslist.retrofit

import com.example.countrieslist.service.CountriesListService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {
    companion object {
        var ret:Int = 2
        private val retrofit: Retrofit by lazy {
            Retrofit.Builder().baseUrl("https://gist.githubusercontent.com/").addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val api: CountriesListService by lazy{
            retrofit.create(CountriesListService::class.java)
        }
    }
}