package com.dj.gittrends.domain.usecase

import com.dj.gittrends.common.ResultState
import com.dj.gittrends.domain.model.Repository
import kotlinx.coroutines.flow.Flow

internal interface IGetRepositoriesUseCase {
    fun invoke(forceFetch: Boolean = false): Flow<ResultState<List<Repository>>>
}
