package com.dentonstudio.rickandmorty.data.remote.dto

import com.dentonstudio.rickandmorty.domain.model.Episode
import com.dentonstudio.rickandmorty.domain.model.Info

data class episodeDto(
    val id:Int,
    val name:String,
    val air_date:String,
    val episode:String,
    val characters : List<String>,
    val url:String,
    val created:String

){
    fun toEpisode():Episode{
        return Episode(
            id = id,
            name= name,
            air_date = air_date,
            episode =episode,
            characters = characters,
            seasonNumber = episode.filter { it.isDigit() }.take(2).toInt(),
            episodeNumber = episode.filter { it.isDigit() }.takeLast(2).toInt()
        )
    }

}

