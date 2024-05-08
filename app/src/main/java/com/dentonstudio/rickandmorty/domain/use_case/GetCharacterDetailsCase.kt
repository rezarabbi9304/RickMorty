package com.dentonstudio.rickandmorty.domain.use_case

import com.dentonstudio.rickandmorty.domain.model.Result
import com.dentonstudio.rickandmorty.domain.repository.CharacterRepository
import com.dentonstudio.rickandmorty.util.Resource
import kotlinx.coroutines.flow.Flow

class GetCharacterDetailsCase(
    val repository: CharacterRepository
) {

    suspend operator fun invoke (id:String):Flow<Resource<Result>>{
      return  repository.getCharacterDetails(id)
    }
}