package com.example.criticsandreviewes.presenter.critics

import androidx.paging.PagingData
import com.example.criticsandreviewes.entity.critics.Crtitcs
import com.example.criticsandreviewes.entity.reviewes.Review
import kotlinx.coroutines.flow.Flow

sealed class CriticsState {
    object Loading: CriticsState()
    data class Success(val listCrititcs: Flow<PagingData<Crtitcs>>?) : CriticsState()
}