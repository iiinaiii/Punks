package com.iiinaiii.punks.domain

import com.iiinaiii.punks.data.Result
import com.iiinaiii.punks.data.beers.BeersRepository
import com.iiinaiii.punks.data.beers.model.toBeer
import com.iiinaiii.punks.domain.model.Beer
import com.iiinaiii.punks.util.exhaustive
import javax.inject.Inject

class GetBeerUseCase @Inject constructor(
    private val beersRepository: BeersRepository
) {

    suspend operator fun invoke(id: Int): Result<Beer> {
        val result = beersRepository.getBeer(id)
        return when (result) {
            is Result.Success -> Result.Success(result.data.toBeer())
            is Result.Error -> result
        }.exhaustive
    }
}