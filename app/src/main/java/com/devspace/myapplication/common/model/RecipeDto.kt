package com.devspace.myapplication.common.model

data class RecipeDto(
    val id: Int,
    val title: String,
    val image: String,
    val summary: String,
    val extendedIngredients: List<IngredientDto>,
    val analyzedInstructions: List<AnalyzedInstructionDto>
)

data class IngredientDto(
    val id: Int,
    val original: String
)

data class AnalyzedInstructionDto(
    val name: String,
    val steps: List<StepDto>
)

data class StepDto(
    val number: Int,
    val step: String
)
