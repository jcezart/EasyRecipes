package com.devspace.myapplication.list.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Center,
                    text = recipe.title,
                    fontSize = 22.sp,
                    fontStyle = FontStyle.Italic,
                    style = TextStyle(
                        shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(2f,2f),
                        blurRadius = 4f))
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ){
                AsyncImage(
                modifier = Modifier
                    .padding(4.dp)
                    .width(410.dp)
                    .height(180.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(2.dp,Color.DarkGray, RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
                model = recipe.image,
                contentDescription = "${recipe.title} Poster Image"
            )}

            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}