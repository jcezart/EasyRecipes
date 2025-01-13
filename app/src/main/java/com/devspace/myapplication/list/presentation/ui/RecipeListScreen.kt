package com.devspace.myapplication.list.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.devspace.myapplication.common.model.RecipeDto
import com.devspace.myapplication.list.presentation.RecipeListViewModel

@Composable
fun RecipeListScreen(viewModel: RecipeListViewModel){

    val randomRecipesList by viewModel.uiRecipes.collectAsState()
    RandomRecipesListContent(
        randomRecipesList = randomRecipesList
    )

}

@Composable
private fun RandomRecipesListContent(
    randomRecipesList: List<RecipeDto>

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            text = "EasyRecipes"
        )

        randomRecipesList.forEach { recipe ->
            Text(
                text = recipe.title,
                modifier = Modifier.padding(8.dp)
            )
            AsyncImage(
                modifier = Modifier
                    .padding(4.dp)
                    .width(120.dp)
                    .height(150.dp),
                contentScale = ContentScale.Crop,
                model = recipe.image,
                contentDescription = "${recipe.title} Poster Image"
            )
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}