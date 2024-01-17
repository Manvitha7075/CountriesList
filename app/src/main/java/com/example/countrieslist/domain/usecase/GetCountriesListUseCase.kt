package com.example.countrieslist.domain.usecase

import com.example.countrieslist.data.repository.CountriesRepository
import com.example.countrieslist.domain.model.CountriesEntity


interface GetCountriesUseCase {
    suspend operator fun invoke(): List<CountriesEntity>?
}

class GetCountriesUseCaseImpl(
    private val countriesRepository: CountriesRepository
) : GetCountriesUseCase {
    override suspend fun invoke(): List<CountriesEntity>? {

        return countriesRepository.getCountriesList()
    }
}