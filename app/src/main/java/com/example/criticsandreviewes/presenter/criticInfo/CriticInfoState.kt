package com.example.criticsandreviewes.presenter.criticInfo

import androidx.paging.PagingData
import com.example.criticsandreviewes.entity.reviewes.Review
import kotlinx.coroutines.flow.Flow

sealed class CriticInfoState {
    object Loading: CriticInfoState()
    data class Success(val listCriticReviewes: Flow<PagingData<Review>>?) : CriticInfoState()
}