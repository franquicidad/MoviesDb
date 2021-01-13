package com.franco.moviesdb.database.similarMovies

import com.franco.moviesdb.domain.SimilarMovies
import com.franco.moviesdb.network.model.SimilarMovies as NetworkSim
import com.franco.moviesdb.database.similarMovies.model.SimilarMovies as DatabaseSim

fun NetworkSim.fromNetToDomain(): SimilarMovies =
        SimilarMovies(
                id ?: 0,
                posterPath ?: "",
                backdropPath ?: "",
                title ?: "",
                overview ?: "",
                releaseDate ?: "",
                originalLanguage ?: "",
                rating ?: 0.0
        )

fun NetworkSim.fromNetToDb(): DatabaseSim =
        DatabaseSim(
                id ?: 0,
                relatedToMovieId = 0,
                backdropPath ?: "",
                originalLanguage ?: "",
                releaseDate ?: "",
                posterPath ?: "",
                title ?: "",
                overview ?: "",
                rating ?: 0.0
        )

fun DatabaseSim.fromDbToDomain(): SimilarMovies =
        SimilarMovies(
                id,
                posterPath ?: "",
                backdropPath ?: "",
                title ?: "",
                overview ?: "",
                releaseDate ?: "",
                originalLanguage ?: "",
                rating ?: 0.0
        )

fun SimilarMovies.fromDomainToDB(): DatabaseSim =
        DatabaseSim(
                id,
                relatedToMovieId = 0,
                backdropPath = backdropPath ?: "",
                originalLanguage = originalLanguage ?: "",
                releaseDate = releaseDate ?: "",
                posterPath ?: "",
                title ?: "",
                overview ?: "",
                rating ?: 0.0
        )