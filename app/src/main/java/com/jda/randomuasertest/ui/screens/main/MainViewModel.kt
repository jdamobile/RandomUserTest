package com.jda.randomuasertest.ui.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jda.randomuasertest.data.Result
import com.jda.randomuasertest.domain.use_case.GetUsersUseCase
import com.jda.randomuasertest.domain.model.toSimpleUser
import com.jda.randomuasertest.ui.entities.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private var searchJob: Job? = null

    private var currentPage = 1

    private var initialList: List<User> = emptyList()

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            getUsersUseCase(currentPage).onEach { result ->
                when (result) {
                    is Result.Success -> {
                        state = state.copy(
                            users = result.data?.map {
                                it.toSimpleUser()
                            } ?: emptyList(),
                            isLoading = false
                        )
                        initialList = state.users
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

    fun onActions(action: UIActions) {
        when (action) {
            UIActions.ShowSearchBar -> {
                state = state.copy(
                    isSearchBarVisible = true
                )
            }

            UIActions.CloseSearchBar -> {
                state = state.copy(
                    isSearchBarVisible = false,
                )
                getUsers()
            }

            is UIActions.SearchInputChange -> {
                state = state.copy(
                    searchText = action.text
                )
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500)
                    searchUsers(action.text)
                }
            }
            UIActions.ClearSearchBar -> {
                state = state.copy(
                    searchText = "",
                    users = initialList
                )
            }
        }
    }

    private fun searchUsers(searchQuery: String) {
        val results = initialList.filter {
            it.displayName.contains(searchQuery, ignoreCase = true)
        }
        state = state.copy(users = results)
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }

    sealed class UIActions {
        data class SearchInputChange(val text: String) : UIActions()
        object ShowSearchBar : UIActions()
        object CloseSearchBar : UIActions()
        object ClearSearchBar : UIActions()

    }
}
