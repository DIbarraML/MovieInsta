package com.example.data.commons

sealed class Output<out T> {
    data class Success<out R>(val value: R) : Output<R>()
    data class Failure(val exception: Exception) : Output<Nothing>()
}
