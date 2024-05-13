package com.dentonstudio.rickandmorty.presentation

import com.dentonstudio.rickandmorty.domain.model.CharacterItem
import com.dentonstudio.rickandmorty.domain.model.Episode
import com.dentonstudio.rickandmorty.domain.model.Info

data class EpisodeState (
   val episodes: List<Episode> = emptyList(),
    val isLoading : Boolean = false

)
