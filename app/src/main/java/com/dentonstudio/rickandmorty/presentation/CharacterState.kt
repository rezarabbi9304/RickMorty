package com.dentonstudio.rickandmorty.presentation

import com.dentonstudio.rickandmorty.domain.model.CharacterItem
import com.dentonstudio.rickandmorty.domain.model.Info

data class CharacterState (
    val allCharacter :CharacterItem = CharacterItem(Info(0,"",0,null), emptyList()),
    val isLoading : Boolean = false

)
