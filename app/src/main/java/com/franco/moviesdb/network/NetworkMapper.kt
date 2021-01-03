package com.franco.moviesdb.network

import com.franco.moviesdb.network.model.MoviesActionModel as NetworkModelMovie
import com.franco.moviesdb.network.model.MoviesComedyModel as NetworkModelMovieComedy
import com.franco.moviesdb.network.model.TvActionModel as NetworkModelTv
import com.franco.moviesdb.database.MovieActionTable as DatabaseModelMovie
import com.franco.moviesdb.database.MovieComedyTable as DatabaseMovieComedy
import com.franco.moviesdb.database.TvActionTable as DatabaseModelTv
import com.franco.moviesdb.database.TvComedyTable as DatabaseModelTvComedy
import com.franco.moviesdb.domain.MovieActionDomain


fun NetworkModelMovie.mapMovieToDomain(): MovieActionDomain =
     MovieActionDomain(id, title, originalLanguage, overview, posterPath, releaseDate, rating)

fun NetworkModelMovieComedy.mapMovieComedyToDomain(): MovieActionDomain =
     MovieActionDomain(id, title, originalLanguage, overview, posterPath, releaseDate, rating)

fun NetworkModelTv.mapTvToDomain(): MovieActionDomain =
     MovieActionDomain(
          id = id,
          title = title,
          originalLanguage = originalLanguage,
          overview = overview,
          posterPath = posterPath,
          releaseDate = releaseDate,
          rating = rating
     )

fun DatabaseModelMovie.mapDatabaseToDomain(): MovieActionDomain =
     MovieActionDomain(
          id,
          title ?: "",
          originalLanguage ?: "",
          overview ?: "",
          posterPath ?: "",
          releaseDate ?: "",
          rating ?: 0.0

     )

fun DatabaseModelTv.mapTvDatabaseToDomain(): MovieActionDomain =
     MovieActionDomain(
          id = id,
          title = title,
          originalLanguage = originalLanguage,
          overview = overview,
          posterPath = posterPath,
          releaseDate = releaseDate,
          rating = rating
     )

fun MovieActionDomain.mapFromDomainToDatabase(): DatabaseModelMovie =
     DatabaseModelMovie(id, title, originalLanguage, overview, posterPath, releaseDate, rating)

fun MovieActionDomain.mapFromDomainToDatabaseComedy(): DatabaseMovieComedy =
     DatabaseMovieComedy(id, title, originalLanguage, overview, posterPath, releaseDate, rating)

fun DatabaseMovieComedy.mapFromDatabaseComedyToDomain(): MovieActionDomain =
     MovieActionDomain(
          id,
          title ?: "",
          originalLanguage ?: "",
          overview ?: "",
          posterPath ?: "",
          releaseDate ?: "",
          rating ?: 0.0

     )


fun MovieActionDomain.mapFromDomainToTvDatabase(): DatabaseModelTv =
     DatabaseModelTv(
          id,
          title ?: "",
          releaseDate ?: "",
          originalLanguage ?: "",
          overview ?: "",
          posterPath ?: "",
          rating ?: 0.0

     )

fun MovieActionDomain.mapFromDomainToTvDatabaseComedy(): DatabaseModelTvComedy =
     DatabaseModelTvComedy(
          id,
          title ?: "",
          releaseDate ?: "",
          originalLanguage ?: "",
          overview ?: "",
          posterPath ?: "",
          rating ?: 0.0

     )


