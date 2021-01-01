package com.franco.moviesdb.network

import com.franco.moviesdb.network.model.MoviesActionModel as NetworkModelMovie
import com.franco.moviesdb.network.model.TvActionModel as NetworkModelTv
import com.franco.moviesdb.database.MovieActionTable as DatabaseModelMovie
import com.franco.moviesdb.database.TvActionTable as DatabaseModelTv
import com.franco.moviesdb.domain.MovieActionDomain


fun NetworkModelMovie.mapMovieToDomain(): MovieActionDomain =
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
          id = id,
          title = title,
          originalLanguage = originalLanguage,
          overview = overview,
          posterPath = posterPath,
          releaseDate = releaseDate,
          rating = rating
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

fun MovieActionDomain.mapFromDomainToTvDatabase(): DatabaseModelTv =
     DatabaseModelTv(id, title, releaseDate, originalLanguage, overview, posterPath, rating)


