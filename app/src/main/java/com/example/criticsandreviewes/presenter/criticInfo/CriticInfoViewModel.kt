package com.example.criticsandreviewes.presenter.criticInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.criticsandreviewes.domain.GetReviewesUseCase
import com.example.criticsandreviewes.entity.reviewes.Review
import com.example.criticsandreviewes.presenter.reviewes.ReviewesState
import com.example.criticsandreviewes.service.adapterReviewes.PagginSourceCriticReviewes
import com.example.criticsandreviewes.service.adapterReviewes.PagginSourceReviewes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CriticInfoViewModel @Inject constructor(private val getReviewesUseCase: GetReviewesUseCase) : ViewModel() {
    private val _state = MutableStateFlow<CriticInfoState>(CriticInfoState.Loading)
    val state = _state.asStateFlow()

    var listCriticsPaggin: Flow<PagingData<Review>>? = null

    fun getCriticReviewes(name:String) {
        _state.value=CriticInfoState.Loading
        listCriticsPaggin = Pager(
            config = PagingConfig(pageSize = 20),
            initialKey = null,
            pagingSourceFactory = { PagginSourceCriticReviewes(name,getReviewesUseCase) },
        ).flow.cachedIn(viewModelScope)
        _state.value =CriticInfoState.Success(listCriticsPaggin)
    }

}