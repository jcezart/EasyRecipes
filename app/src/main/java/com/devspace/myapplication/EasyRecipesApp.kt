package com.devspace.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.devspace.myapplication.detail.presentation.RecipeDetailViewModel
import com.devspace.myapplication.detail.presentation.ui.RecipeDetailScreen
import com.devspace.myapplication.list.presentation.RecipeListViewModel
import com.devspace.myapplication.list.presentation.ui.RecipeListScreen

@Composable
fun EasyRecipesApp(listViewModel: RecipeListViewModel, detailViewModel: RecipeDetailViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "recipeList") {
        composable(route = "recipeList"){
            RecipeListScreen(listViewModel, navController)
        }
        composable(
            route = "recipeDetail"+ "/{itemId}",
            arguments = listOf(navArgument("itemId") {
                type = NavType.StringType
            })
        ){backStackEntry ->
            val recipeId = requireNotNull(backStackEntry.arguments?.getString("itemId"))
            RecipeDetailScreen(detailViewModel, navController, recipeId)
        }
    }
}