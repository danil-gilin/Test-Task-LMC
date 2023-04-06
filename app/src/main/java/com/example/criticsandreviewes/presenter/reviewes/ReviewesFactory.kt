package com.example.criticsandreviewes.presenter.reviewes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ReviewesFactory @Inject constructor(private val reviewesViewModel: ReviewesViewModel):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return reviewesViewModel as T
    }
}