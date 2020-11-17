package com.franco.moviesdb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.franco.moviesdb.data.network.MovieResponce.MoviesActionResponce

class HomeViewModel : ViewModel() {
    
    private val _responce=MutableLiveData <String>()//moviesActionResponce
    val responce : LiveData <String> get()=_responce

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    init{
        getActionMovies()
    }

    private fun getActionMovies() {
        _responce.value = "any"
    }
}