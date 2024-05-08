package com.dentonstudio.rickandmorty.data.remote.dto

import com.dentonstudio.rickandmorty.domain.model.Info

data class InfoDto(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any?
){
    fun toInfo():Info{
        return Info(
            count = count,
            next  =next,
            pages = pages,
            prev = prev
        )
    }
}