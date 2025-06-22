package com.example.pexelsapp.screens.details.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun TopBarView(
    photographer: String,
    onBackPress: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        BackButton(onClick = onBackPress)
        Title(
            photographer = photographer,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_back),
            tint = MaterialTheme.colorScheme.onSecondary,
            contentDescription = null,
        )
    }
}

@Composable
fun Title(
    photographer: String,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Text(
        text = photographer,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        maxLines = 1,
        color = color,
        modifier = modifier
    )
}

@Composable
fun SelectedPhoto(
    photo: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = photo,
        contentDescription = null,
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun DownloadButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .size(height = 48.dp, width = 180.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .clickable { onClick() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.primary),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_download),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = null
            )
        }

        Text(
            text = stringResource(R.string.download),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}


@Composable
fun BookmarkButton(
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(48.dp)
            .background(MaterialTheme.colorScheme.secondary, CircleShape)
    ) {
        Icon(
            painter = painterResource(if (isSelected) R.drawable.ic_bookmark_filled else R.drawable.ic_bookmark),
            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondary,
            contentDescription = null,
        )
    }
}

@Composable
fun ImageNotFoundView(
    onExploreClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.image_not_found),
            fontSize = 14.sp,
        )
        TextButton(
            onClick = onExploreClick
        ) {
            Text(
                text = stringResource(R.string.explore),
                fontSize = 18.sp,
            )
        }
    }
}