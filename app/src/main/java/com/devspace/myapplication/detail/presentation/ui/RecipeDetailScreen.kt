package com.devspace.myapplication.detail.presentation.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.devspace.myapplication.common.model.RecipeDto
import com.devspace.myapplication.detail.presentation.RecipeDetailViewModel

@Composable
fun RecipeDetailScreen(
    detailViewModel: RecipeDetailViewModel,
    navHostController: NavHostController,
    recipeId: String,
){

    val recipeDto by detailViewModel.uiDetail.collectAsState()
    detailViewModel.fetchRecipeDetail(recipeId)


    recipeDto?.let {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(onClick =  {
                    detailViewModel.cleanRecipeId()
                    navHostController.popBackStack()
                }){
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = it.title,
                    fontSize = 24.sp
                )
            }
            RecipeDetailContent(it)
        }
    }
}

@Composable
private fun RecipeDetailContent(recipe: RecipeDto) {
    // Estado para controlar a aba selecionada
    var selectedTab by remember { mutableStateOf("Ingredients") }

    // Textos para os ingredientes e modo de preparo com formatação
    val ingredientsText = buildAnnotatedString {
        recipe.extendedIngredients.forEachIndexed { index, ingredient ->
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)) {
                append("${index + 1}. ")
            }
            append(ingredient.original)
            append("\n")
        }
    }

    val cookingInstructions = buildAnnotatedString {
        recipe.analyzedInstructions.forEach { instruction ->
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)) {
                append("${instruction.name}\n")
            }
            instruction.steps.forEach { step ->
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Step ${step.number}: ")
                }
                append(step.step)
                append("\n")
            }
            append("\n") // Separação entre seções de instruções
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Card com a imagem
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.CenterHorizontally)
                .shadow(8.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                model = recipe.image,
                contentDescription = "${recipe.title} Poster Image"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botões para alternar entre ingredientes e modo de preparo
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { selectedTab = "Ingredients" },
                colors = if (selectedTab == "Ingredients") ButtonDefaults.buttonColors(containerColor = Color.Gray) else ButtonDefaults.buttonColors()
            ) {
                Text("Ingredients")
            }

            Button(
                onClick = { selectedTab = "Cooking" },
                colors = if (selectedTab == "Cooking") ButtonDefaults.buttonColors(containerColor = Color.Gray) else ButtonDefaults.buttonColors()
            ) {
                Text("Cooking")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Conteúdo da aba selecionada
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            item {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = if (selectedTab == "Ingredients") ingredientsText else cookingInstructions,
                    fontSize = 18.sp
                )
            }
        }
    }
}

