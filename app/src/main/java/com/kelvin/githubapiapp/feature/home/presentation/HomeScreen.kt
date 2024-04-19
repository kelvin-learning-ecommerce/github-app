package com.kelvin.githubapiapp.feature.home.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kelvin.githubapiapp.feature.favorite.presentation.FavoriteScreen
import com.kelvin.githubapiapp.feature.popular.presentation.PopularScreen
import com.kelvin.githubapiapp.ui.theme.GithubApiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun HomeScreen(
    navController: NavHostController,
) {
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Popular", "Favorite")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                )
            }
        }
        when (tabIndex) {
            0 -> PopularScreen(navController = navController)
            1 -> FavoriteScreen(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GithubApiAppTheme {
        HomeScreen(
            navController = rememberNavController()
        )
    }
}
