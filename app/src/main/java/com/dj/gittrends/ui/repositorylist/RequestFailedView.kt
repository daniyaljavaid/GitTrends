package com.dj.gittrends.ui.repositorylist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.dj.gittrends.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RequestFailedView() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        it

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.retry)
            )

            LottieAnimation(
                composition = composition,
                modifier = Modifier
                    .size(300.dp),
            )
        }
    }
}

