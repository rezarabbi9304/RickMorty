package com.dentonstudio.rickandmorty.presentation.CharacterDetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dentonstudio.rickandmorty.domain.use_case.GetCharacterDetailsCase
import com.dentonstudio.rickandmorty.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    val useCase:GetCharacterDetailsCase
):ViewModel() {


    private val _charcterDetailsState = mutableStateOf(CharacterDetailsState())

    val details = _charcterDetailsState


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
                    }
                }
            }.launchIn(this)
        }
    }
}