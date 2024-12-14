package com.example.fliprfastfood.util

sealed class Resource<T> {
    class Loading<out T> : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: String) : Resource<Nothing>()

    }

