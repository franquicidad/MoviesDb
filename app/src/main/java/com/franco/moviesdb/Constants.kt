package com.franco.moviesdb

const val BASE_URL = "https://api.themoviedb.org/3/discover/"
const val APPEND_API = "?api_key=7d51874568317dfd0c91db399be2bdec"
const val IMAGE_URL = "http://image.tmdb.org/t/p/w342"

const val ALONE_API = "7d51874568317dfd0c91db399be2bdec"
const val APPEND_TO_SELECT_GENRES = "&with_genres="
const val BASE_URL_TV_POPULAR = "https://api.themoviedb.org/3/tv/popular?api_key="
const val APPEND_MOVIE = "movie"
const val APPEND_TV = "tv"

const val ACTION = 28
const val COMEDY = 35

const val MOVIE_URL_ACTION = BASE_URL + APPEND_MOVIE + APPEND_API + APPEND_TO_SELECT_GENRES + ACTION
const val MOVIE_URL_COMEDY = BASE_URL + APPEND_MOVIE + APPEND_API + APPEND_TO_SELECT_GENRES + COMEDY

const val TV_URL_ACTION = BASE_URL + APPEND_TV + APPEND_API + APPEND_TO_SELECT_GENRES + ACTION
const val TV_URL_COMEDY = BASE_URL + APPEND_TV + APPEND_API + APPEND_TO_SELECT_GENRES + COMEDY
//https://api.themoviedb.org/3/tv/popular?api_key=7d51874568317dfd0c91db399be2bdec&page=1

//https://api.themoviedb.org/3/discover/movie?api_key=7d51874568317dfd0c91db399be2bdec&with_genres=28
//https://api.themoviedb.org/3/discover/tv?api_key=7d51874568317dfd0c91db399be2bdec&with_genres=28

