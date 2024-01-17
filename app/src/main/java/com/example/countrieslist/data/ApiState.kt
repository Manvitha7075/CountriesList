package com.example.countrieslist.data

/*
* ApiState: A sealed class representing different states of an API response
* */
sealed class ApiState<T> (
    val data: T ?= null,
    val message: String? = null
){
    class Success <T> (data: T) : ApiState<T>(data)
    class Error<T>(message: String) : ApiState<T>( message = message)
    class Loading<T> : ApiState<T>()
}

