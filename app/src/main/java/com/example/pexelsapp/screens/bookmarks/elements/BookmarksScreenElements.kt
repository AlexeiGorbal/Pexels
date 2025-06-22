package com.example.pexelsapp.screens.bookmarks.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.pexelsapp.R
import com.example.pexelsapp.domain.Photo

@Composable
fun Title(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.bookmarks),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = modifier
    )
}

@Composable
fun ListBookmarks(
    photos: List<Photo>,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(bottom = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(17.dp),
        verticalItemSpacing = 15.dp,
        modifier = modifier
    ) {
        items(photos) { photo ->
            BookmarkItem(
                photo = photo,
                photographer = photo.photographer,
                onClick = { onClick(it) },
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
            )
        }
    }
}

@Composable
fun BookmarkItem(
    photo: Photo,
    photographer: String,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier
    ) {
        AsyncImage(
            model = photo.url,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable { onClick(photo.id) },
            placeholder = painterResource(R.drawable.ic_photo_placeholder)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .alpha(0.5f)
                .background(Color.Black)
                .height(33.dp)
                .fillMaxWidth()
        ) {
            Text(text = photographer, maxLines = 1)
        }
    }
}

@Composable
fun NothingSavedView(
    onExploreClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.havent_saved),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        TextButton(onClick = onExploreClick) {
            Text(
                text = stringResource(R.string.explore),
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}