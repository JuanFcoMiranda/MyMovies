package com.jfma75.mymovies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jfma75.mymovie.domain.Movie
import com.jfma75.mymovies.ui.common.ScopedViewModel
import com.jfma75.mymovies.usecases.GetMovieById
import kotlinx.coroutines.launch
class DetailViewModel(val imdbId: String, val getMovieById: GetMovieById) : ScopedViewModel() {
    class DetailUIModel(val movie: Movie)

    private val _model = MutableLiveData<DetailUIModel>()
    val model: LiveData<DetailUIModel>
        get() {
            if (_model.value == null) findMovie()
            return _model
        }

    init {
        initScope()
    }

    private fun findMovie() = launch {
        _model.value = DetailUIModel(getMovieById.invoke(imdbId))
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}