package com.example.mywordle.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mywordle.network.data.OnlineWordleRepository
import com.example.mywordle.ui.user.RegisterUserViewModel
import com.example.mywordle.ui.home.HomeDestination
import com.example.mywordle.ui.home.HomeScreen
import com.example.mywordle.ui.online.game.OnlineSinglePlayerScreen
import com.example.mywordle.ui.online.game.OnlineSinglePlayerScreenDestination
import com.example.mywordle.ui.online.game.OnlineSinglePlayerView
import com.example.mywordle.ui.online.ui.MultiPlayerScreen
import com.example.mywordle.ui.online.ui.MultiPlayerScreenDestination
import com.example.mywordle.ui.word.WordDetailsDestination
import com.example.mywordle.ui.word.WordDetailsScreen
import com.example.mywordle.ui.word.WordEditDestination
import com.example.mywordle.ui.word.WordEditScreen
import com.example.mywordle.ui.word.WordEntryDestination
import com.example.mywordle.ui.word.WordEntryScreen
import com.example.mywordle.ui.online.ui.MultiplayerMyWordleView
import com.example.mywordle.ui.user.RegisterUserScreen
import com.example.mywordle.ui.user.RegisterUserScreenDestination

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun WordNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController, startDestination = HomeDestination.route, modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen (navigateToWordEntry = { navController.navigate(WordEntryDestination.route) },
                navigateToWordUpdate = {
                    navController.navigate("${WordDetailsDestination.route}/${it}")
                },
                        navigateToMultiplayerScreen = {
                    navController.navigate(MultiPlayerScreenDestination.route)
                },
                navigateToUserRegister = {
                    navController.navigate(RegisterUserScreenDestination.route)
                },
                navigateToSinglePlayer = {
                    navController.navigate(OnlineSinglePlayerScreenDestination.route)
                }
            )
        }
        composable(route = WordEntryDestination.route) {
            WordEntryScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
        composable(
            route = WordDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(WordDetailsDestination.WORD_ID) {
                type = NavType.IntType
            })
        ) {
            WordDetailsScreen(
                navigateToEditWord =
                {
                    navController.navigate("${WordEditDestination.route}/$it")
                },
                navigateBack = { navController.navigateUp() })
        }
        composable(
            route = WordEditDestination.routeWithArgs,
            arguments = listOf(navArgument(WordEditDestination.WORD_ID) {
                type = NavType.IntType
            })
        ) {
            WordEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
        composable(
            route = MultiPlayerScreenDestination.route
        ) {
            val multiplayerMyWordleView: MultiplayerMyWordleView =
                viewModel(factory = MultiplayerMyWordleView.Factory)
            MultiPlayerScreen(
                multiplayerUiState = multiplayerMyWordleView.multiplayerUiState,
                retryAction = multiplayerMyWordleView::getAllWords,
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = RegisterUserScreenDestination.route
        ) {
            val registerUserViewModel: RegisterUserViewModel =
                viewModel(factory = RegisterUserViewModel.Factory)
            RegisterUserScreen(
                userViewModel = registerUserViewModel,
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = OnlineSinglePlayerScreenDestination.route
        ) {
            val onlineSinglePlayerView: OnlineSinglePlayerView = viewModel(factory = OnlineSinglePlayerView.Factory)
            OnlineSinglePlayerScreen(
                onlineSinglePlayerView = onlineSinglePlayerView,
                navigateBack = { navController.popBackStack() },
            )
        }
    }
}