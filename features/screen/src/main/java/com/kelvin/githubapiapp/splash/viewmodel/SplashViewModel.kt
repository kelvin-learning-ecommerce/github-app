package com.kelvin.githubapiapp.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.kelvin.githubapiapp.shared.NavigationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
) : ViewModel() {

    fun toHomePage(navController: NavHostController) {
        viewModelScope.launch {

            delay(2000)

            navController.navigate(NavigationItem.Home.route)
        }
    }
}
