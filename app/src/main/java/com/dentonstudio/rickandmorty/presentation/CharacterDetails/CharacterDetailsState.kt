package com.dentonstudio.rickandmorty.presentation.CharacterDetails

import com.dentonstudio.rickandmorty.domain.model.CharacterItem
import com.dentonstudio.rickandmorty.domain.model.Info
import com.dentonstudio.rickandmorty.domain.model.Result

data class CharacterDetailsState (
    val charaterDetails : Result?=null,
    val isLoading : Boolean = false

)