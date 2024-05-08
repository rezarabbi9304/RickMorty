package com.dentonstudio.rickandmorty.domain.model

data class CharacterItem(
    val info: Info,
    val results: List<Result>
)