package com.example.criticsandreviewes.service.adapterCritics

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.criticsandreviewes.domain.GetCriticsUseCase
import com.example.criticsandreviewes.entity.critics.Crtitcs
import javax.inject.Inject

class PaggingSourceCritics (val query:String, private val getCriticsUseCase: GetCriticsUseCase) : PagingSource<Int, Crtitcs>() {
    override fun getRefreshKey(state: PagingState<Int, Crtitcs>): Int? {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Crtitcs> {
        val page = params.key ?: 0
        val rezult= kotlin.runCatching {
            getCriticsUseCase.getCritics(query,page)
        }.fold(
            onSuccess = {
                Log.d("ReviewesS","onSuccess")
                LoadResult.Page(
                    data = it ?: emptyList(),
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 20
                )
            },
            onFailure = {error->
                Log.d("PaggingSourceCritics", "onCreateView: ${error.message}")
                LoadResult.Error(error)
            }
        )

        return rezult
    }
}