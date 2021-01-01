package com.franco.moviesdb.ui.movieDetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.franco.moviesdb.domain.Repository
import com.franco.moviesdb.domain.RepositoryImpl

class DetailViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

}