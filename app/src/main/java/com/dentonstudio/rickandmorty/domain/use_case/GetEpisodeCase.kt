package com.dentonstudio.rickandmorty.domain.use_case

import com.dentonstudio.rickandmorty.domain.model.Episode
import com.dentonstudio.rickandmorty.domain.model.Result
import com.dentonstudio.rickandmorty.domain.repository.CharacterRepository
import com.dentonstudio.rickandmorty.util.Resource
import kotlinx.coroutines.flow.Flow

class GetEpisodeCase(
    val repository: CharacterRepository
) {

    suspend operator fun invoke (id:String):Flow<Resource<List<Episode>>>{
      return  repository.getEpisodeDetails(id)
    }
}