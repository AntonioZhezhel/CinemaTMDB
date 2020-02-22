package usecases

import com.example.domain.MoviesCache
import common.Transformer
import entities.MovieEntity
import io.reactivex.Observable

class GetFavoriteMovies(transformer: Transformer<List<MovieEntity>>,
                        private val moviesCache: MoviesCache
): UseCase<List<MovieEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<MovieEntity>> {
        return moviesCache.getAll()
    }

}