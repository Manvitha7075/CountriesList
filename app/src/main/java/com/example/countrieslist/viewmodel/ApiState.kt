package com.example.countrieslist.viewmodel

sealed class ApiState<T> (
    val data: T ?= null,
    val message: String? = null
){
    class Success <T> (data: T) : ApiState<T> (data)
    class Error<T>() : ApiState<T>()
    class Loading<T> : ApiState<T>()
}

