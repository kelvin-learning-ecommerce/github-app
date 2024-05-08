package com.kelvin.githubapiapp.popular

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kelvin.githubapiapp.R
import com.kelvin.githubapiapp.model.UserUIModel

@Composable
fun PopularItemList(
    user: UserUIModel,
    onItemClick: (UserUIModel) -> Unit,
    onItemFav: ((UserUIModel) -> Unit?)? = null,
    isFavorite: Boolean = false
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        border = BorderStroke(width = 2.dp, Color.LightGray),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
            .fillMaxSize()
            .background(Color.White)
            .clickable {
                onItemClick(user)
            }
    ) {
        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth().padding(15.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .align(Alignment.CenterStart),
            ) {
                AsyncImage(

                    model = ImageRequest.Builder(LocalContext.current)
                        .data(user.avatarUrl)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Fit,
                    contentDescription = "Icon",
                    modifier = Modifier
                        .size(150.dp)
                        .weight(0.3f)
                )

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .weight(0.7f),
                ) {
                    Text(
                        text = "Login name: ${user.login}",
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    Text(
                        text = "Repo URL: ${user.repoUrl}",
                    )
                }
            }
            if (!isFavorite) {
                Icon(imageVector = if (!user.isFavorite) {
                    Icons.Default.FavoriteBorder
                } else {
                    Icons.Default.Favorite
                }, contentDescription = "Favorite Icon", tint = Color.Red,
                    modifier = Modifier
                        .clickable {
                            onItemFav?.let { it(user) }
                        }
                        .align(Alignment.TopEnd)
                        .padding(15.dp)
                        .size(30.dp)
                )
            }
        }
    }
}
