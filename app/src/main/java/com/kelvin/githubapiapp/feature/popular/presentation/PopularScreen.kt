package com.kelvin.githubapiapp.feature.popular.presentation

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kelvin.githubapiapp.NavigationItem
import com.kelvin.githubapiapp.feature.popular.viewmodel.PopularViewModel
import com.kelvin.githubapiapp.shared.utils.OnLifecycleEvent
import com.kelvin.githubapiapp.shared.utils.ShimmerListItem
import com.kelvin.githubapiapp.ui.theme.GithubApiAppTheme

private val buffer = 1

@Composable
fun PopularScreen(viewModel: PopularViewModel = hiltViewModel(), navController: NavHostController) {

    val state by viewModel.state.collectAsState()
    val paginationState by viewModel.paginationState.collectAsState()

    val listState = rememberLazyListState()

    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - buffer
        }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom) viewModel.loadMoreUser()
    }

    OnLifecycleEvent { owner, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                viewModel.getUsers()

            }

            else -> {}
        }
    }

    if (state.isLoading) {
        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(10) {
                ShimmerListItem()
            }
        }
    } else {
        Box {
            LazyColumn(
                Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 16.dp),
                state = listState
            ) {
                items(state.data.size) { i ->
                    PopularItemList(
                        user = state.data[i],
                        onItemClick = {
                            navController.navigate(NavigationItem.UserDetail.route)
                        },
                        onItemFav = {
//                            onItemFav(it)
                        },
                    )
                }
            }

            if (paginationState.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PopularPreview() {
    GithubApiAppTheme {
        PopularScreen(
            navController = rememberNavController()
        )
    }
}
