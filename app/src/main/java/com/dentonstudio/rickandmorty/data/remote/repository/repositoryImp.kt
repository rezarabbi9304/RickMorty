package com.dentonstudio.rickandmorty.data.remote.repository

import com.dentonstudio.rickandmorty.data.remote.CharacterApi
import com.dentonstudio.rickandmorty.data.remote.dto.CharacterItemDto
import com.dentonstudio.rickandmorty.domain.model.CharacterItem
import com.dentonstudio.rickandmorty.domain.model.Result
import com.dentonstudio.rickandmorty.domain.repository.CharacterRepository
import com.dentonstudio.rickandmorty.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class repositoryImp @Inject constructor (
    private val api: CharacterApi
):CharacterRepository {
    override suspend fun getCharacter(): Flow<Resource<CharacterItem>> = flow {

       emit(Resource.Loading())

        try {
            val characterData = api.getCharacter()
            emit(Resource.Success(data = characterData.toCharacterItem()))
        }catch (ex:HttpException){
            emit(Resource.Error(
                message = "this is wrong ${ex}"
            ))
        }

    }

    override suspend fun getCharacterDetails(id: String): Flow<Resource<Result>>  = flow{
        emit(Resource.Loading())

        try {
            val detailsData = api.getCharacterDetails(id)

            emit(Resource.Success(
                data = detailsData.toResult()
            ))

        }catch (Ex:HttpException){
            emit(Resource.Error(
                message = "We found ${Ex}"
            ))
        }
    }
}