package com.kelvin.githubapiapp.userdetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvin.githubapiapp.model.UserDetailModel
import com.kelvin.githubapiapp.userdetail.state.UserDetailState
import com.kelvin.githubapiapp.utils.Resource
import com.kelvin.githubappapi.usecase.UserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val userDetailUseCase: UserDetailUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(UserDetailState())
    val state = _state.asStateFlow()

    fun getUserDetail(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!state.value.isLoading) {
                userDetailUseCase.execute(id)
                    .distinctUntilChanged()
                    .collectLatest { result ->
                        when (result) {
                            is Resource.Success -> result.data?.let { data ->
                                onRequestSuccess(data)
                            }

                            is Resource.Error -> onRequestError(result.message)
                            is Resource.Loading -> onRequestLoading()
                        }
                    }
            }
        }
    }

    internal fun onRequestSuccess(
        data: UserDetailModel
    ) {
        updateState(
            data = data,
            isLoading = false,
            error = ""
        )
    }

    internal fun onRequestError(msg: String?) {
        updateState(error = msg)
    }

    internal fun onRequestLoading() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
    }

    fun updateState(
        isLoading: Boolean = false,
        data: UserDetailModel? = null,
        error: String? = null
    ) {
        _state.update {
            it.copy(
                isLoading = isLoading,
                data = data,
                error = error
            )
        }
    }
}
