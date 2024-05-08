package com.kelvin.githubapiapp.populardetail

import com.google.common.truth.Truth.assertThat
import com.kelvin.githubapiapp.dispatcher.MainDispatcherRule
import com.kelvin.githubapiapp.domain.usecase.UserDetailUseCase
import com.kelvin.githubapiapp.userdetail.viewmodel.UserDetailViewModel
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PopularDetailViewModelTests {
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var userDetailUseCase: UserDetailUseCase
    private lateinit var viewModel: UserDetailViewModel

    @Before
    fun setUp() {
        userDetailUseCase = mockk(relaxed = true)
        viewModel = UserDetailViewModel(userDetailUseCase)
    }

    @Test
    fun `onRequestLoading when movie detail state is not empty update isLoading state to true`() =
        runTest {
            viewModel.updateState(data = dummyPopularDetailModel)
            viewModel.onRequestLoading()

            val isLoadingState = viewModel.state.value.isLoading

            assertThat(isLoadingState).isTrue()
        }

    @Test
    fun `when error message is not null update error state`() = runTest {
        val errorMsg = "Hi This is an Error"

        viewModel.onRequestError(errorMsg)

        val errorState = viewModel.state.value.error

        assertThat(errorState).isNotEmpty()
    }

    @Test
    fun `when getMovieData called and result is Success`() = runTest {
        viewModel.onRequestSuccess(dummyPopularDetailModel)

        val movieData = viewModel.state.value.data

        assertThat(movieData).isNotNull()
    }
}
