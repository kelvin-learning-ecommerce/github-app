package com.kelvin.githubapiapp.feature.popular.viewmodel

import androidx.lifecycle.ViewModel
import com.kelvin.githubapiapp.feature.popular.state.PopularState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
) : ViewModel() {

    private val _state = MutableStateFlow(PopularState())
    val state = _state.asStateFlow()

}
