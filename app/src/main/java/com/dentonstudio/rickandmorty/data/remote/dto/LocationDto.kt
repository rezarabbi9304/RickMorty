package com.dentonstudio.rickandmorty.data.remote.dto

import com.dentonstudio.rickandmorty.domain.model.Location

data class LocationDto(
    val name: String,
    val url: String
){
    fun toLocation ():Location{
        return Location(
            name = name,
            url = url
        )
    }
}