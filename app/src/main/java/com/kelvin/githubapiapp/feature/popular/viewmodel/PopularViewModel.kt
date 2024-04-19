package com.kelvin.githubapiapp.feature.popular.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvin.githubapiapp.data.model.UserModel
import com.kelvin.githubapiapp.data.model.UserUIModel
import com.kelvin.githubapiapp.data.model.updateIsFavorite
import com.kelvin.githubapiapp.data.query.UserListQuery
import com.kelvin.githubapiapp.data.room.model.FavoriteDaoModel
import com.kelvin.githubapiapp.domain.usecase.DeleteFavoriteUseCase
import com.kelvin.githubapiapp.domain.usecase.SaveFavoriteUseCase
import com.kelvin.githubapiapp.domain.usecase.UsersUseCase
import com.kelvin.githubapiapp.feature.popular.state.PopularState
import com.kelvin.githubapiapp.shared.utils.PaginationState
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
class PopularViewModel @Inject constructor(
    private val userUseCase: UsersUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) : ViewModel() {

    private var page: Int = 0

    private val _state = MutableStateFlow(PopularState())
    val state = _state.asStateFlow()

    private val _paginationState = MutableStateFlow(PaginationState())
    val paginationState = _paginationState.asStateFlow()

    fun getUsers(search: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
//            onRequestSuccess(dummyUser)
            if (!state.value.isLoading) {
                userUseCase.execute(UserListQuery(since = page, search = search))
                    .distinctUntilChanged()
                    .collectLatest { result ->
                        when (result) {
                            is Resource.Success -> result.data?.let { data ->
                                onRequestSuccess(data)
                            }

                            is Resource.Error -> onRequestError(result.message)
                            is Resource.Loading -> onRequestLoading(page > 0)
                        }
                    }
            }
        }
    }

    fun loadMoreUser() {
        getUsers()
    }

    fun searchUser(search: String) {
        page = 0
        updateState(
            data = listOf(),
        )
        getUsers(search)
    }

    fun saveFavorite(ctx: Context, user: UserUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val model = FavoriteDaoModel(
                id = user.id,
                avatarUrl = user.avatarUrl,
                login = user.login
            )

            if (user.isFavorite) {
                model.id?.let {
                    deleteFavoriteUseCase.execute(it)
                }
            } else {
                saveFavoriteUseCase.execute(model)
            }

            val list = _state.value.data.toList()

            val uiData = list.updateIsFavorite(user.id, !user.isFavorite)

            updateState(
                isLoading = false,
                data = uiData,
            )

            _paginationState.update {
                it.copy(
                    isLoading = false
                )
            }
        }

        Toast.makeText(
            ctx,
            if (user.isFavorite) "Success Remove from Favorite list" else "Success Add to Favorite list",
            Toast.LENGTH_SHORT
        ).show()
    }

    internal fun onRequestSuccess(
        data: List<UserModel>
    ) {
        page += 10

        val uiData = data.map { user ->
            UserUIModel(
                id = user.id ?: 0,
                login = user.login ?: "",
                avatarUrl = user.avatarUrl ?: ""
            )
        }

        val stateData = _state.value.data + uiData

        updateState(
            data = stateData,
            isLoading = false,
            error = ""
        )

        _paginationState.update {
            it.copy(
                isLoading = false
            )
        }
    }

    internal fun onRequestError(msg: String?) {

    }

    internal fun onRequestLoading(isLoadMore: Boolean) {
        if (isLoadMore) {
            if (_state.value.data.isNotEmpty()) {
                _paginationState.update {
                    it.copy(
                        isLoading = true
                    )
                }
            }
        } else {
            if (_state.value.data.isEmpty()) {
                _state.update {
                    it.copy(
                        isLoading = true
                    )
                }
            }
        }
    }

    fun updateState(
        isLoading: Boolean = false,
        data: List<UserUIModel> = emptyList(),
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
