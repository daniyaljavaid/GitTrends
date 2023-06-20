package com.dj.gittrends.ui.repositorylist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.dj.gittrends.R
import com.dj.gittrends.ui.theme.ColorDarkGreen


@Composable
internal fun RetryButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(10),
        border = BorderStroke(1.dp, ColorDarkGreen)
    ) {
        Text(text = stringResource(id = R.string.retry), style = TextStyle(color = ColorDarkGreen))
    }
}

