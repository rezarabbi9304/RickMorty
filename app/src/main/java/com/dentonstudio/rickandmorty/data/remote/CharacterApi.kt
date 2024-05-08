package com.dentonstudio.rickandmorty.data.remote

import com.dentonstudio.rickandmorty.data.remote.dto.CharacterItemDto
import com.dentonstudio.rickandmorty.data.remote.dto.ResultDto
import com.dentonstudio.rickandmorty.util.Resource
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApi {
    @GET("api/character")
   suspend fun getCharacter():CharacterItemDto


    @GET("api/character/{id}")
    suspend fun getCharacterDetails(@Path ("id") id:String):ResultDto
}