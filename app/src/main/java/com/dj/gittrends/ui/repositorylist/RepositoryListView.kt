package com.dj.gittrends.ui.repositorylist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dj.gittrends.domain.model.Repository


@Composable
internal fun RepositoryListView(
    data: List<Repository>
) {
    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier
            .fillMaxSize()
    ) {
        (data).forEach { repository ->
            item {
                RepositoryListItem(
                    repository
                )
            }
        }
    }
}

