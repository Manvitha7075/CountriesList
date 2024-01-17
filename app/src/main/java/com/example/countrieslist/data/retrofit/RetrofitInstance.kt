package com.example.countrieslist.data.retrofit

import com.example.countrieslist.data.service.CountriesListService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {
    companion object {
        private val retrofit: Retrofit by lazy {
            Retrofit.Builder().baseUrl("https://gist.githubusercontent.com/").addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val api: CountriesListService by lazy{
            retrofit.create(CountriesListService::class.java)
        }
    }
}
