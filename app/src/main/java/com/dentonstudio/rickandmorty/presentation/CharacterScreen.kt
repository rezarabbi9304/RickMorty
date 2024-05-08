package com.dentonstudio.rickandmorty.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dentonstudio.rickandmorty.R
import com.dentonstudio.rickandmorty.presentation.component.HomeViewModel
import com.dentonstudio.rickandmorty.presentation.component.ItemScreen

@Composable()
fun CharacterScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF272B33))
        .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(Color.White)

            .padding(30.dp)
            ){
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.rick_and_morty),
                    contentDescription = null, // decorative element,

                )
            }

        }

        if(viewModel.state.value.isLoading){
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()

        ){
                items(viewModel.state.value.allCharacter.results.size){it->
                        val charachter = viewModel.state.value.allCharacter.results[it]
                    Spacer(modifier = Modifier.height(8.dp))

                    ItemScreen(
                        charachter
                    )


                }
        }
    }


}