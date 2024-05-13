package com.dentonstudio.rickandmorty.domain.model


data class Episode(
    val id:Int,
    val name:String,
    val air_date:String,
    val episode:String,
    val characters : List<String>,
    val seasonNumber:Int,
    val episodeNumber:Int


)

