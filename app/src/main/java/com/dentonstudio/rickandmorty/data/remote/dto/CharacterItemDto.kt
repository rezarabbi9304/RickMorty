package com.dentonstudio.rickandmorty.data.remote.dto

import com.dentonstudio.rickandmorty.domain.model.CharacterItem

data class CharacterItemDto(
    val info: InfoDto,
    val results: List<ResultDto>
){

    fun toCharacterItem(): CharacterItem{
        return  CharacterItem(
            info = info.toInfo(),
            results = results.map { it.toResult() }
        )
    }
}