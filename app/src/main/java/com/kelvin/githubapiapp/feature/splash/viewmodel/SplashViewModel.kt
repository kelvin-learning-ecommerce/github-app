package com.kelvin.githubapiapp.feature.splash.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvin.githubapiapp.feature.home.presentation.HomeActivity
import com.kelvin.githubapiapp.shared.utils.freshStartActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
) : ViewModel() {


    fun toHomePage(ctx: Context) {
        viewModelScope.launch {

            delay(2000)

            ctx.freshStartActivity(HomeActivity::class.java)
        }
    }
}
