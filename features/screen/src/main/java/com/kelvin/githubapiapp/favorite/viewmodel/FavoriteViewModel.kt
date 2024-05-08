package com.kelvin.githubapiapp.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvin.githubapiapp.favorite.state.FavoriteState
import com.kelvin.githubapiapp.model.UserModel
import com.kelvin.githubapiapp.model.UserUIModel
import com.kelvin.githubapiapp.utils.Resource
import com.kelvin.githubappapi.repository.FavoriteRepository
import com.kelvin.githubappapi.usecase.FetchFavoriteUseCase
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
class FavoriteViewModel @Inject constructor(
//    private val fetchFavoriteUseCase: FetchFavoriteUseCase,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state = _state.asStateFlow()

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.getAll()
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
//            fetchFavoriteUseCase.execute(Unit)
//                .distinctUntilChanged()
//                .collectLatest { result ->
//                    when (result) {
//                        is Resource.Success -> result.data?.let { data ->
//                            onRequestSuccess(data.map {
//                                UserModel(
//                                    id = it.id,
//                                    login = it.login,
//                                    avatarUrl = it.avatarUrl
//                                )
//                            })
//                        }
//
//                        is Resource.Error -> onRequestError(result.message)
//                        is Resource.Loading -> onRequestLoading()
//                    }
//                }
        }
    }

    internal fun onRequestSuccess(
        data: List<UserModel>
    ) {

        val uiData = data.map { user ->
            UserUIModel(
                id = user.id ?: 0,
                login = user.login ?: "",
                avatarUrl = user.avatarUrl ?: "",
                repoUrl = user.reposUrl ?: ""
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
        _state.update {
            it.copy(
                error = msg
            )
        }
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
