package com.kelvin.githubapiapp.feature.popular.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvin.githubapiapp.NavigationItem
import com.kelvin.githubapiapp.data.model.UserModel
import com.kelvin.githubapiapp.data.query.UserListQuery
import com.kelvin.githubapiapp.domain.repository.UserRepository
import com.kelvin.githubapiapp.domain.usecase.UsersUseCase
import com.kelvin.githubapiapp.feature.popular.state.PopularState
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
class PopularViewModel @Inject constructor(
    private val userUseCase: UsersUseCase,
) : ViewModel() {

    private var page: Int = 0

    private val _state = MutableStateFlow(PopularState())
    val state = _state.asStateFlow()

    private val _paginationState = MutableStateFlow(PaginationState())
    val paginationState = _paginationState.asStateFlow()

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            onRequestSuccess(dummyUser)
//            userUseCase.execute(UserListQuery(since = page))
//                .distinctUntilChanged()
//                .collectLatest { result ->
//                    when (result) {
//                        is Resource.Success -> result.data?.let { data ->
//                            onRequestSuccess(data)
//                        }
//
//                        is Resource.Error -> onRequestError(result.message)
//                        is Resource.Loading -> onRequestLoading(page > 0)
//                    }
//                }
        }
    }

    fun loadMoreUser(){
        viewModelScope.launch {

            onRequestLoading(page > 0)

            delay(2000)

            onRequestSuccess(dummyUser)

        }
    }

    internal fun onRequestSuccess(
        data: List<UserModel>
    ) {
        page += 10

        val movieData = _state.value.data + data
        _state.update {
            it.copy(
                data = movieData,
                isLoading = false,
                error = ""
            )
        }

        _paginationState.update {
            it.copy(
                isLoading = false
            )
        }
    }

    internal fun onRequestError(msg: String?) {

    }

    internal fun onRequestLoading(isLoadMore: Boolean) {
        if(isLoadMore){
            if (_state.value.data.isNotEmpty()) {
                _paginationState.update {
                    it.copy(
                        isLoading = true
                    )
                }
            }
        }else{
            if (_state.value.data.isEmpty()) {
                _state.update {
                    it.copy(
                        isLoading = true
                    )
                }
            }
        }
    }

}
