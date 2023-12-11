package com.jda.randomuasertest.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.jda.randomuasertest.ui.entities.User
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onItemClick: (User) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val eventFlow = viewModel.eventFlow
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true ) {
        eventFlow.collectLatest { event ->
            when (event) {
                is MainViewModel.UIEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    UsersApp {
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Scaffold(
                topBar = { MainAppBar(title = "CONTACTOS", onBackPressed = { }) },
                snackbarHost = { SnackbarHost(snackBarHostState) }
            ) { padding ->
                UserList(
                    onUserClick = onItemClick,
                    modifier = Modifier.padding(padding),
                    users = state.users,
                    viewModel = viewModel
                )
            }
        }
    }
}
