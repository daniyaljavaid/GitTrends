package com.dj.gittrends.ui.repositorylist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dj.gittrends.common.ResultState
import com.dj.gittrends.domain.model.Repository
import com.dj.gittrends.domain.usecase.IGetRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class RepositoryListViewModel @Inject constructor(
    getRepositoriesUseCase: IGetRepositoriesUseCase
) : ViewModel() {

    val repositoryListUiState: StateFlow<RepositoryListUiState> =
        getRepositoriesUseCase.invoke().map { result ->
            when (result) {
                is ResultState.Success -> {
                    RepositoryListUiState.GetRepositoriesSuccess(result.data)
                }

                is ResultState.Error -> {
                    RepositoryListUiState.Error
                }
            }
        }.stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(), RepositoryListUiState.Loading
        )

}

internal sealed interface RepositoryListUiState {
    object Loading : RepositoryListUiState
    object Error : RepositoryListUiState
    data class GetRepositoriesSuccess(val data: List<Repository>) : RepositoryListUiState
}