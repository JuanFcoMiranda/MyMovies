package com.jfma75.mymovies.ui.common

import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jfma75.mymovies.exception.Failure
import com.jfma75.mymovies.ui.common.Scope

abstract class ScopedViewModel : ViewModel(), Scope by Scope.Impl() {
    var failure: MutableLiveData<Failure> = MutableLiveData()

    init {
        this.initScope()
    }

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}