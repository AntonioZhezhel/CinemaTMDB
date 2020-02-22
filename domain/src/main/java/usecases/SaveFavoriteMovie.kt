package usecases

import com.example.domain.MoviesCache
import common.Transformer
import entities.MovieEntity
import io.reactivex.Observable
import java.lang.IllegalArgumentException

import java.util.*

class SaveFavoriteMovie(transformer: Transformer<Boolean>,
                        private val moviesCache: MoviesCache
): UseCase<Boolean>(transformer) {

    companion object {
        private const val PARAM_MOVIE_ENTITY = "param:movieEntity"
    }

    override fun createObservable(data: Map<String, Any>?): Observable<Boolean> {

        val movieEntity = data?.get(PARAM_MOVIE_ENTITY)

        movieEntity?.let {
            return Observable.fromCallable {
                val entity = it as MovieEntity
                moviesCache.save(entity)
                return@fromCallable true
            }
        }?: return Observable.error({ IllegalArgumentException("MovieEntity must be provided.") })

    }

    fun save(movieEntity: MovieEntity): Observable<Boolean> {
        val data = HashMap<String, MovieEntity>()
        data[PARAM_MOVIE_ENTITY] = movieEntity
        return observable(data)
    }
}

