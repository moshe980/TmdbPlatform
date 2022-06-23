package com.giniapps.tmdbplatform.model.response

sealed class Result<out T : Any>

//Success
data class Success<out T : Any>(val data: T) : Result<T>()

//Failure
data class Failure(val exc: Throwable?) : Result<Nothing>()