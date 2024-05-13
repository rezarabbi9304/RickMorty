package com.dentonstudio.rickandmorty.domain.repository

import com.dentonstudio.rickandmorty.data.remote.dto.CharacterItemDto
import com.dentonstudio.rickandmorty.domain.model.CharacterItem
import com.dentonstudio.rickandmorty.domain.model.Episode
import com.dentonstudio.rickandmorty.domain.model.Result
import com.dentonstudio.rickandmorty.util.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    suspend fun getCharacter():Flow<Resource<CharacterItem>>

    suspend fun getCharacterDetails( id:String):Flow<Resource<Result>>
    suspend fun getEpisodeDetails( id:String):Flow<Resource<List<Episode>>>
}