package com.dentonstudio.rickandmorty.data.remote.dto

import com.dentonstudio.rickandmorty.domain.model.Origin

data class OriginDto(
    val name: String,
    val url: String
){
    fun toOrigin():Origin{
        return  Origin(
            name = name,
            url = url
        )
    }
}