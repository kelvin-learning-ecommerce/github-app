package com.kelvin.githubapiapp.feature.splash.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kelvin.githubapiapp.R
import com.kelvin.githubapiapp.feature.splash.viewmodel.SplashViewModel
import com.kelvin.githubapiapp.ui.theme.GithubApiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SplashScreen(
    navController: NavHostController,
    vm: SplashViewModel = hiltViewModel()
) {

    vm.toHomePage(navController)

    GithubApiAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.github_ic),
                    contentDescription = "Github Logo"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    GithubApiAppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.github_ic),
                contentDescription = "Github Logo"
            )
        }
    }
}
