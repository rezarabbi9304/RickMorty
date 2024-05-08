package com.dentonstudio.rickandmorty.presentation.component

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dentonstudio.rickandmorty.domain.use_case.getCharacterCase
import com.dentonstudio.rickandmorty.presentation.CharacterState
import com.dentonstudio.rickandmorty.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val useCase: getCharacterCase
) : ViewModel() {


    private val _state = mutableStateOf(CharacterState())
    val state = _state


    init {
        getCharacter()
    }


    fun getCharacter() {
        viewModelScope.launch {
            useCase.invoke().onEach { resource ->
                when (resource) {
                    is Resource.Error -> {

                        Log.d("Error", ": " + resource.message)

                    }

                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        Log.d("Success", ": " + resource.data)

                        _state.value = resource.data?.let {
                            state.value.copy(
                                allCharacter = it,
                                isLoading = false
                            )
                        }!!
                    }
                }

            }.launchIn(this)
        }
    }
}