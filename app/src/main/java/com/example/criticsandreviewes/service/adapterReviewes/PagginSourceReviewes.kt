package com.example.criticsandreviewes.service.adapterReviewes

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.criticsandreviewes.domain.GetReviewesUseCase
import com.example.criticsandreviewes.entity.reviewes.Review
import javax.inject.Inject

class PagginSourceReviewes (
    val query: String,
    val date: String,
    private val getReviewesUseCase: GetReviewesUseCase
) : PagingSource<Int, Review>() {

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return INITIAL_KEY
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        val offset = params.key ?: INITIAL_KEY
        val limit = params.loadSize

        val result = kotlin.runCatching {
            getReviewesUseCase.getReviewesSearch(query, date, offset)
        }
        return result.fold(
            onSuccess = { reviews ->
                LoadResult.Page(
                    data = reviews.results ?: emptyList(),
                    prevKey = if (offset == 0) null else offset - limit,
                    nextKey = if (reviews.results.isNullOrEmpty() || !reviews.hasMore) null else offset + limit
                )
            },
            onFailure = { error ->
                Log.d("PagginSourceReviewes", "onCreateView: ${error.message}")
                LoadResult.Error(error)
            }
        )
    }

    companion object{
      const val  INITIAL_KEY=0
    }

}