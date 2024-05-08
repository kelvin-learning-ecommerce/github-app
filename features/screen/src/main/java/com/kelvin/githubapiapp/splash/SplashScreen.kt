package com.kelvin.githubapiapp.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kelvin.githubapiapp.R
import com.kelvin.githubapiapp.splash.viewmodel.SplashViewModel
import com.kelvin.githubapiapp.theme.GithubApiAppTheme

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
//                Image(
//                    painter = painterResource(R.drawable.github_ic),
//                    contentDescription = "Github Logo"
//                )
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
            Icon(imageVector = Icons.Default.Face, contentDescription = "Splash Screen Logo",
                tint = Color.Blue)
        }
    }
}
