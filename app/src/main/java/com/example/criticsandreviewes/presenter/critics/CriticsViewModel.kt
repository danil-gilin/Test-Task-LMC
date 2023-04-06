package com.example.criticsandreviewes.presenter.critics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.criticsandreviewes.domain.GetCriticsUseCase
import com.example.criticsandreviewes.entity.critics.Crtitcs
import com.example.criticsandreviewes.service.adapterCritics.PaggingSourceCritics
import com.example.criticsandreviewes.service.adapterReviewes.PagginSourceReviewes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CriticsViewModel @Inject constructor(private val getCriticsUseCase: GetCriticsUseCase) : ViewModel() {

    private val _state= MutableStateFlow<CriticsState>(CriticsState.Loading)
    val state = _state.asStateFlow()

    var listCritics: Flow<PagingData<Crtitcs>>? = null


    fun getCritics(query: String) {
        viewModelScope.launch {
                _state.value = CriticsState.Loading
                listCritics = Pager(
                    config = PagingConfig(pageSize = 20),
                    initialKey = null,
                    pagingSourceFactory = { PaggingSourceCritics(query, getCriticsUseCase) }
                ).flow.cachedIn(viewModelScope)
                _state.value = CriticsState.Success(listCritics)
        }

    }

}