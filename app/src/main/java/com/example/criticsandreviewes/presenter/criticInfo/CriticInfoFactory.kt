package com.example.criticsandreviewes.presenter.criticInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class CriticInfoFactory @Inject constructor(private val criticInfoViewModel: CriticInfoViewModel) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return criticInfoViewModel as T
    }
}