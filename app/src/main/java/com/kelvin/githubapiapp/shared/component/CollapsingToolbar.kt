package com.kelvin.githubapiapp.shared.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kelvin.githubapiapp.data.model.UserDetailModel
import com.kelvin.githubapiapp.feature.userdetail.viewmodel.UserDetailViewModel
import com.kelvin.githubapiapp.shared.utils.OnLifecycleEvent

private val headerHeight = 250.dp

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun CollapsingToolbar(
    modifier: Modifier = Modifier,
    viewModel: UserDetailViewModel,
    id: Int
) {

    val scroll: ScrollState = rememberScrollState(0)

    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }

    val state = viewModel.state.collectAsState()

    OnLifecycleEvent { owner, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                viewModel.getUserDetail(id)
            }

            else -> {}
        }
    }


    if (state.value.isLoading) {
        UserDetailShimmerListItem()
    } else {

        Box(modifier = modifier) {
            Header(
                scroll = scroll,
                headerHeightPx = headerHeightPx,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(headerHeight),
                data = state.value.data
            )
            Body(
                scroll = scroll,
                modifier = Modifier.fillMaxSize(),
                data = state.value.data
            )
        }
    }
}

@Composable
private fun Header(
    scroll: ScrollState,
    headerHeightPx: Float,
    modifier: Modifier = Modifier,
    data: UserDetailModel?
) {
    Column {
        Box(
            modifier = modifier
                .graphicsLayer {
                    translationY = -scroll.value.toFloat() / 2f // Parallax effect
                    alpha = (-1f / headerHeightPx) * scroll.value + 1
                }
        ) {

            AsyncImage(

                model = ImageRequest.Builder(LocalContext.current)
                    .data(data?.avatarUrl)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Fit,
                contentDescription = "backdrop image",
                modifier = Modifier
                    .align(Alignment.Center)
            )

            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 3 * headerHeightPx / 4 // Gradient applied to wrap the title only
                        )
                    )
            )
        }
    }
}

@Composable
fun Body(
    modifier: Modifier = Modifier,
    scroll: ScrollState = rememberScrollState(0),
    data: UserDetailModel?,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(scroll)
            .padding(top = 20.dp)
    ) {
        Spacer(Modifier.height(headerHeight))
        Row {
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(0.4F)
            ) {
            }
            Column {
                modifier
                    .weight(0.6F)
                    .padding(horizontal = 10.dp)

            }
        }

        LazyRow(
            Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
        }
    }
}
