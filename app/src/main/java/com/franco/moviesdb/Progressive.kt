package com.franco.moviesdb

interface Progressive {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}