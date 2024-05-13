package com.dentonstudio.rickandmorty.presentation.CharacterDetails

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dentonstudio.rickandmorty.domain.use_case.GetCharacterDetailsCase
import com.dentonstudio.rickandmorty.domain.use_case.GetEpisodeCase
import com.dentonstudio.rickandmorty.presentation.EpisodeState
import com.dentonstudio.rickandmorty.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.logging.Handler
import javax.inject.Inject
import kotlin.concurrent.timerTask

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    val useCase:GetCharacterDetailsCase,
    val episodeUseCase:GetEpisodeCase,
    savedStateHandle: SavedStateHandle,
):ViewModel() {


    private val _charcterDetailsState = mutableStateOf(CharacterDetailsState())

    val details = _charcterDetailsState

    private val _stateEpisodes = mutableStateOf(EpisodeState())
    val stateEpisode = _stateEpisodes

    private val _episodes = mutableStateOf("");

    val episode = _episodes;


    init {
        savedStateHandle.get<Int>("CharacterId")?.let {
            if(it!=-1){
                getDetails(it.toString())
            }


        }
    }


    fun getDetails(id:String){
        viewModelScope.launch {
            useCase.invoke(id).onEach {resource ->
                when(resource){
                    is Resource.Error -> {

                    }
                    is Resource.Loading -> {
                        _charcterDetailsState.value = details.value.copy(
                           isLoading = true
                        )

                    }
                    is Resource.Success -> {
                        _charcterDetailsState.value = details.value.copy(
                            charaterDetails = resource.data,
                            isLoading = false
                        )
                        val  episodes = _charcterDetailsState.value.charaterDetails?.episode;
                        Log.d("episodes", "getDetails: " + episodes?.let { getepisodesList(it) } )

                        _episodes.value = episodes?.let { getepisodesList(it) }.toString()
                        getAllEpisodes(_episodes.value)

                    }
                }
            }.launchIn(this)
        }
    }

    fun getAllEpisodes(episodeIds:String){
        viewModelScope.launch {
            episodeUseCase.invoke(episodeIds).onEach {result->
                when(result){
                    is Resource.Error ->{}
                    is Resource.Loading ->{
                        _stateEpisodes.value = stateEpisode.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        result.data?.let {
                            _stateEpisodes.value = stateEpisode.value.copy(
                                episodes = result.data,
                                isLoading = false
                            )
                        }


                    }
                }
            }.launchIn(this)
        }

    }



    fun getepisodesList(episodeIds:List<String>) :String{
        val episodeNumbers = mutableListOf<Int>()

        for (episode in episodeIds){
            val parts = episode.split("/")
            val episodeNumberStr = parts.last()

            val episodeNumber = episodeNumberStr.toIntOrNull()
            episodeNumber?.let { episodeNumbers.add(it) }
        }
        val episodeAllId = episodeNumbers.joinToString(",")
        return episodeAllId
    }
}