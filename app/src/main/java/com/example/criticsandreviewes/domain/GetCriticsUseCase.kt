package com.example.criticsandreviewes.domain

import com.example.criticsandreviewes.data.repositry.CriticsRepository
import javax.inject.Inject

class GetCriticsUseCase @Inject constructor(private val criticsRepository: CriticsRepository) {
    suspend fun getCritics(query: String,offset: Int=0) = criticsRepository.getCritics(query,offset)
}