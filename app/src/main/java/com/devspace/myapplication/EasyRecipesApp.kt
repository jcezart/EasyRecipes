package com.devspace.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devspace.myapplication.list.presentation.RecipeListViewModel
import com.devspace.myapplication.list.presentation.ui.RecipeListScreen

@Composable
fun EasyRecipesApp(listViewModel: RecipeListViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "recipeList") {
        composable(route = "recipeList"){
            RecipeListScreen(listViewModel)
        }
    }
}