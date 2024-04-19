package com.kelvin.githubapiapp.feature.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvin.githubapiapp.data.model.UserModel
import com.kelvin.githubapiapp.data.model.UserUIModel
import com.kelvin.githubapiapp.data.query.UserListQuery
import com.kelvin.githubapiapp.domain.usecase.FetchFavoriteUseCase
import com.kelvin.githubapiapp.domain.usecase.UsersUseCase
import com.kelvin.githubapiapp.feature.favorite.state.FavoriteState
import com.kelvin.githubapiapp.shared.utils.PaginationState
import com.kelvin.githubapiapp.shared.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val fetchFavoriteUseCase: FetchFavoriteUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state = _state.asStateFlow()

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchFavoriteUseCase.execute(Unit)
                .distinctUntilChanged()
                .collectLatest { result ->
                    when (result) {
                        is Resource.Success -> result.data?.let { data ->
                            onRequestSuccess(data.map {
                                UserModel(
                                    id = it.id,
                                    login = it.login,
                                    avatarUrl = it.avatarUrl
                                )
                            })
                        }

                        is Resource.Error -> onRequestError(result.message)
                        is Resource.Loading -> onRequestLoading()
                    }
                }
        }
    }

    internal fun onRequestSuccess(
        data: List<UserModel>
    ) {

        val uiData = data.map { user ->
            UserUIModel(
                id = user.id ?: 0,
                login = user.login ?: "",
                avatarUrl = user.avatarUrl ?: ""
            )
        }

        _state.update {
            it.copy(
                data = uiData,
                isLoading = false,
                error = ""
            )
        }
    }

    internal fun onRequestError(msg: String?) {

    }

    internal fun onRequestLoading() {
        if (_state.value.data.isEmpty()) {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
        }
    }

}
