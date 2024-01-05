package com.example.countrieslist.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {
    companion object {
        val retrofit by lazy {
            Retrofit.Builder().baseUrl("https://gist.githubusercontent.com/").addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val api by lazy{
            retrofit.create(CountriesListService::class.java)
        }
    }
}