package com.example.criticsandreviewes.data.repositry

import android.util.Log
import com.example.criticsandreviewes.data.api.RetrofitReviewAndCritics
import com.example.criticsandreviewes.entity.critics.CriticsRezult
import com.example.criticsandreviewes.entity.critics.Crtitcs
import javax.inject.Inject

class CriticsRepository @Inject constructor() {
    private var localCritics: List<Crtitcs> = emptyList()
    private var searchCritics: List<Crtitcs> = emptyList()

    suspend fun getCritics(query: String, offset: Int): List<Crtitcs> {
        try {
        if (localCritics.isEmpty() && query.isEmpty()) {
            localCritics = RetrofitReviewAndCritics.criticsApi.getCritics().results ?: emptyList()
        }

        if (query.isNotEmpty() && searchCritics.isEmpty()) {
            val result = RetrofitReviewAndCritics.criticsApi.getCriticsSearch(query).results ?: emptyList()
            if (result.isNotEmpty()) {
                searchCritics = result
            } else {
                searchCritics = localCritics.filter { it.displayName.contains(query) }
            }
        }

        val criticsList = when {
            query.isEmpty() -> localCritics
            searchCritics.isEmpty() -> emptyList()
            else -> searchCritics
        }

        if (offset >= criticsList.size) {
            searchCritics = emptyList()
        }

        return criticsList.subList(offset.coerceAtMost(criticsList.size), (offset + 20).coerceAtMost(criticsList.size))
        } catch (e: Exception) {
            Log.d("CriticsRepository", "getCritics: ${e.message}")
            return emptyList()
        }
    }
}