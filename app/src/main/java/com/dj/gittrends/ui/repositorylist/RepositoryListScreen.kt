package com.dj.gittrends.ui.repositorylist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dj.gittrends.ui.repositorylist.viewmodel.RepositoryListUiState
import com.dj.gittrends.ui.repositorylist.viewmodel.RepositoryListViewModel

@Composable
internal fun RepositoryListScreen(
    viewModel: RepositoryListViewModel = hiltViewModel()
) {
    val repositoryListUiState by viewModel.repositoryListUiState.collectAsStateWithLifecycle()

    RepositoryListScreen(
        repositoryListUiState = repositoryListUiState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RepositoryListScreen(
    repositoryListUiState: RepositoryListUiState,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        it

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            RequestFailedView()
        }
    }
}




