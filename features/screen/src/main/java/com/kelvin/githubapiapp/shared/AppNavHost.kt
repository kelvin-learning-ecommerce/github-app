package com.kelvin.githubapiapp.shared

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kelvin.githubapiapp.splash.SplashScreen
import com.kelvin.githubapiapp.home.HomeScreen
import com.kelvin.githubapiapp.userdetail.UserDetailScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Splash.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Splash.route) {
            SplashScreen(navController)
        }
        composable(NavigationItem.Home.route) {
            HomeScreen(navController)
        }
        composable(
            NavigationItem.UserDetail.route + "/{id}", arguments = listOf(
            navArgument("id") {
                type = NavType.IntType
                defaultValue = 0
            }
        )) {
            val id = it.arguments?.getInt("id") ?: 0
            UserDetailScreen(id = id)
        }
    }
}
