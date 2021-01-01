package com.franco.moviesdb.util

interface Progressive {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}