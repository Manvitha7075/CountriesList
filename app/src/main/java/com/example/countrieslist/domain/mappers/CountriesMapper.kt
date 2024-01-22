package com.example.countrieslist.domain.mappers

import com.example.countrieslist.domain.entity.CountriesEntity
import com.example.countrieslist.data.model.CountriesList

/*
* CountriesMapper: Maps a response of type CountriesList to a list of CountriesEntity
* */
object CountriesMapper {
    fun buildFrom(response: CountriesList?):List<CountriesEntity>?{
           return response?.map{
                   CountriesEntity(it.name, it.capital, it.region, it.code)
        }
    }
}