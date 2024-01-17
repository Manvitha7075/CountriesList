package com.example.countrieslist.domain.mappers

import com.example.countrieslist.domain.model.CountriesEntity
import com.example.countrieslist.data.model.CountriesList

object CountriesMapper {
    fun buildFrom(response: CountriesList):List<CountriesEntity>{

           return response.map{
                   CountriesEntity(it.name, it.capital, it.region, it.code)
        }
    }
}