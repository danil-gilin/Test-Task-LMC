package com.example.criticsandreviewes.presenter.reviewes

import androidx.paging.PagingData
import com.example.criticsandreviewes.entity.reviewes.Review
import kotlinx.coroutines.flow.Flow

sealed class ReviewesState{
    object Loading:ReviewesState()
    data class Success(val listSearchPaggin: Flow<PagingData<Review>>?) :ReviewesState()
    data class Error(val error:String):ReviewesState()
}
