package com.example.countrieslist.domain.usecase

import com.example.countrieslist.data.ApiState
import com.example.countrieslist.data.repository.CountriesRepository
import com.example.countrieslist.domain.entity.CountriesEntity


interface GetCountriesUseCase {
    suspend operator fun invoke(): ApiState<List<CountriesEntity>>
}

class GetCountriesUseCaseImpl(
    private val countriesRepository: CountriesRepository
) : GetCountriesUseCase {
    override suspend fun invoke():ApiState<List<CountriesEntity>> {
        return countriesRepository.getCountriesList()
    }
}