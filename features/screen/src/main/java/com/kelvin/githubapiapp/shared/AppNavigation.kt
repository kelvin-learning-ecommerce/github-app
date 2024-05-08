package com.kelvin.githubapiapp.shared

enum class Screen {
    SPLASH,
    HOME,
    USERDETAIL,
}
sealed class NavigationItem(val route: String) {
    data object Splash : NavigationItem(Screen.SPLASH.name)
    data object Home : NavigationItem(Screen.HOME.name)
    data object UserDetail : NavigationItem(Screen.USERDETAIL.name)
}
