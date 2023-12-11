package com.jda.randomuasertest.ui.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jda.randomuasertest.data.Result
import com.jda.randomuasertest.domain.use_case.GetUsersUseCase
import com.jda.randomuasertest.domain.model.toSimpleUser
import com.jda.randomuasertest.ui.entities.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    var state by mutableStateOf(MainState(isLoading = true))
        private set

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentPage = 1

    init {
        getUsers(increase = false)
    }

    private fun getUsers(increase: Boolean) {
        viewModelScope.launch {
            if (increase) {
                currentPage++
            } else if (currentPage > 1) {
                currentPage--
            }

            getUsersUseCase(currentPage).onEach { result ->
                when (result) {
                    is Result.Success -> {
                        state = state.copy(
                            users = result.data?.map {
                                it.toSimpleUser()
                            } ?: emptyList(),
                            isLoading = false
                        )
                    }

                    is Result.Error -> {
                        state = state.copy(
                            isLoading = false
                        )
                        _eventFlow.emit(
                            UIEvent.ShowSnackBar(
                                result.message ?: "Unknown error"
                            )
                        )
                    }

                    is Result.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }
}