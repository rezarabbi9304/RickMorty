package com.dentonstudio.rickandmorty.data.remote.repository

import com.dentonstudio.rickandmorty.data.remote.CharacterApi
import com.dentonstudio.rickandmorty.data.remote.dto.CharacterItemDto
import com.dentonstudio.rickandmorty.domain.model.CharacterItem
import com.dentonstudio.rickandmorty.domain.model.Episode
import com.dentonstudio.rickandmorty.domain.model.Result
import com.dentonstudio.rickandmorty.domain.repository.CharacterRepository
import com.dentonstudio.rickandmorty.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
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
        }catch (ex:IOException){
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
        }catch (ex:IOException){
            emit(Resource.Error(
                message = "this is wrong ${ex}"
            ))
        }
    }

    override suspend fun getEpisodeDetails(id: String): Flow<Resource<List<Episode>>> = flow{
        emit(Resource.Loading())

        try {


            val payLoad = api.getEpisodeDetails(id+",0").map { it.toEpisode() }
            emit(Resource.Success(
                data = payLoad
            ))

        }catch (Ex:HttpException){
            emit(Resource.Error(
                message = "something went wrong${Ex}"
            ))
        }

    }
}