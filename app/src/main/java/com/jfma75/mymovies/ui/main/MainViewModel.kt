package com.jfma75.mymovies.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jfma75.mymovie.domain.Movie
import com.jfma75.mymovies.ui.common.ScopedViewModel
import com.jfma75.mymovies.usecases.GetMovies
import kotlinx.coroutines.launch

class MainViewModel(private val getMovies: GetMovies) : ScopedViewModel() {
    private val _model = MutableLiveData<MainUIModel>()
    val model: LiveData<MainUIModel>
        get() {
            return _model
        }

    sealed class MainUIModel {
        object Loading : MainUIModel()
        class Content(val movies: List<Movie>) : MainUIModel()
        class Navigation(val movie: Movie) : MainUIModel()
    }

    init {
        initScope()
    }

    fun onMovieSearchPerformed(searchTerm: String) {
        launch {
            _model.value = MainUIModel.Loading
            _model.value = MainUIModel.Content(getMovies.invoke(searchTerm))
        }
    }

    fun onMovieClicked(movie: Movie) {
        _model.value = MainUIModel.Navigation(movie)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}