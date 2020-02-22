package usecases

import com.example.domain.MoviesRepository
import common.Transformer
import entities.MovieEntity
import io.reactivex.Observable

open class GetPopularMovies(transformer: Transformer<List<MovieEntity>>,
                            private val moviesRepository: MoviesRepository
) : UseCase<List<MovieEntity>>(transformer) {
    override fun createObservable(data: Map<String, Any>?): Observable<List<MovieEntity>> {
        return moviesRepository.getMovies()
    }

}