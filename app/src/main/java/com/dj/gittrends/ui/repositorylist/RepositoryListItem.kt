package com.dj.gittrends.ui.repositorylist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.dj.gittrends.R
import com.dj.gittrends.domain.model.Repository

@Composable
internal fun RepositoryListItem(
    repository: Repository
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        val (avatar, ownerName, repoName, repoDesc, starIcon, startCount, divider) = createRefs()

        AsyncImage(
            model = repository.owner.avatarURL,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(
                    avatar
                ) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .size(40.dp)
                .clip(CircleShape)
        )

        Text(
            text = repository.owner.login,
            modifier = Modifier
                .constrainAs(
                    ownerName
                ) {
                    top.linkTo(avatar.top)
                    start.linkTo(avatar.end)
                }
                .padding(start = 12.dp),
            style = TextStyle(fontSize = 12.sp)
        )

        Text(
            text = repository.owner.login,
            modifier = Modifier
                .constrainAs(
                    repoName
                ) {
                    top.linkTo(ownerName.bottom)
                    start.linkTo(ownerName.start)
                }
                .padding(start = 12.dp, top = 2.dp),
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
        )

        Text(
            text = repository.description,
            modifier = Modifier
                .constrainAs(
                    repoDesc
                ) {
                    top.linkTo(repoName.bottom)
                    start.linkTo(repoName.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(start = 12.dp, top = 5.dp),
            style = TextStyle(fontSize = 12.sp), maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Image(
            painter = painterResource(id = R.drawable.grade_24dp),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 8.dp, top = 5.dp)
                .size(20.dp)
                .constrainAs(
                    starIcon
                ) {
                    top.linkTo(repoDesc.bottom)
                    start.linkTo(repoDesc.start)
                }
        )

        Text(
            text = repository.stargazersCount.toString(),
            modifier = Modifier
                .padding(start = 4.dp, top = 2.dp)
                .constrainAs(
                    startCount
                ) {
                    top.linkTo(starIcon.top)
                    bottom.linkTo(starIcon.bottom)
                    start.linkTo(starIcon.end)
                },
            style = TextStyle(fontSize = 12.sp)
        )

        Divider(thickness = 1.dp, color = Color.LightGray,
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(
                    divider
                ) {
                    top.linkTo(starIcon.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
    }
}

