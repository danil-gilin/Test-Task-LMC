package com.example.criticsandreviewes.presenter.critics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class CriticsFactory @Inject constructor(private val criticsViewModel: CriticsViewModel) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return criticsViewModel as T
    }
}