package com.kelvin.githubapiapp.feature.userdetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvin.githubapiapp.data.model.UserDetailModel
import com.kelvin.githubapiapp.data.model.UserModel
import com.kelvin.githubapiapp.data.model.UserUIModel
import com.kelvin.githubapiapp.data.query.UserListQuery
import com.kelvin.githubapiapp.domain.usecase.UserDetailUseCase
import com.kelvin.githubapiapp.domain.usecase.UsersUseCase
import com.kelvin.githubapiapp.feature.userdetail.state.UserDetailState
import com.kelvin.githubapiapp.shared.utils.Resource
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
        error: String = ""
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
