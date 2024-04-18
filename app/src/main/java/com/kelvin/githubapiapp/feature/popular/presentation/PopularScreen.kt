package com.kelvin.githubapiapp.feature.popular.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kelvin.githubapiapp.feature.popular.viewmodel.PopularViewModel
import com.kelvin.githubapiapp.shared.utils.ShimmerListItem
import com.kelvin.githubapiapp.ui.theme.GithubApiAppTheme

@Composable
fun PopularScreen(viewModel: PopularViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

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
        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
//            items(state.value.genreList.size) { i ->
//                ProductCard(genreItem = state.value.genreList[i])
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PopularPreview() {
    GithubApiAppTheme {
        PopularScreen()
    }
}
