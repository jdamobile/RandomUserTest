package com.jda.randomuasertest.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jda.randomuasertest.data.network.UserApiServiceFactory
import com.jda.randomuasertest.data.network.model.asDomainModel
import com.jda.randomuasertest.domain.model.toSimpleUser
import com.jda.randomuasertest.ui.entities.User
import com.jda.randomuasertest.ui.screens.details.DetailScreen
import com.jda.randomuasertest.ui.screens.main.MainScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val service = UserApiServiceFactory.makeUserApiService()
    var usersState by rememberSaveable { mutableStateOf(emptyList<User>()) }

    LaunchedEffect(Unit) {
        usersState = service.getUsersList(100)
            .asDomainModel().map {
                it.toSimpleUser()
            }
    }
    NavHost(
        navController = navController,
        startDestination = NavItem.Main.route
    ) {
        composable(NavItem.Main) {
            MainScreen(
                onItemClick = {
                    navController.navigate(
                            NavItem.Detail.createRoute(
                                it.photo,
                                it.displayName,
                                it.email,
                                it.gender,
                                it.dateJoined,
                                it.phone,
                                it.latitude,
                                it.longitude
                            )
                    )
                },
                users = usersState
            )
        }
        composable(NavItem.Detail) {backStackEntry ->
            DetailScreen(
                onBackPressed = { navController.popBackStack() },
                photo = backStackEntry.findArg(NavArg.Photo),
                name = backStackEntry.findArg(NavArg.Name),
                email = backStackEntry.findArg(NavArg.Email),
                gender = backStackEntry.findArg(NavArg.Gender),
                date = backStackEntry.findArg(NavArg.DateJoined),
                phone = backStackEntry.findArg(NavArg.Phone),
                latitude = backStackEntry.findArg(NavArg.Latitude),
                longitude = backStackEntry.findArg(NavArg.Longitude),
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.getString(arg.key)
    requireNotNull(value)
    return value as T
}

