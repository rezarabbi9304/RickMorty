package com.dentonstudio.rickandmorty.domain.use_case

import com.dentonstudio.rickandmorty.domain.model.CharacterItem
import com.dentonstudio.rickandmorty.domain.repository.CharacterRepository
import com.dentonstudio.rickandmorty.util.Resource
import kotlinx.coroutines.flow.Flow

class getCharacterCase(
    val repository: CharacterRepository
) {
    suspend operator  fun invoke():Flow<Resource<CharacterItem>>{
        return  repository.getCharacter()

    }
}