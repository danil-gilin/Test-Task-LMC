package com.example.criticsandreviewes.presenter.reviewes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.criticsandreviewes.domain.GetReviewesUseCase
import com.example.criticsandreviewes.entity.reviewes.Review
import com.example.criticsandreviewes.service.adapterReviewes.PagginSourceReviewes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReviewesViewModel @Inject constructor(private val getReviewesUseCase: GetReviewesUseCase) : ViewModel() {
    private val _state= MutableStateFlow<ReviewesState>(ReviewesState.Loading)
    val state=_state.asStateFlow()

    var listSearchPaggin: Flow<PagingData<Review>>? = null

    fun getReviewesSearch(search: String, date: String) {
        viewModelScope.launch {
            try {
                _state.value = ReviewesState.Loading
                val date1 = date.split(" / ")
                val date2 = "${date1[0]}-${date1[1]}-${date1[2]}"
                val date3 = "${date1[0].toInt() - 1}-${date1[1]}-${date1[2]}"
                val dateRezult = "$date3:$date2"

                listSearchPaggin = Pager(
                    config = PagingConfig(pageSize = 20),
                    initialKey = null,
                    pagingSourceFactory = {
                        PagginSourceReviewes(
                            search,
                            dateRezult,
                            getReviewesUseCase
                        )
                    },
                ).flow.cachedIn(viewModelScope)

                _state.value = ReviewesState.Success(listSearchPaggin)
            }catch (e:Throwable){
                _state.value  = ReviewesState.Error(e.message.toString())
            }
        }
    }
}