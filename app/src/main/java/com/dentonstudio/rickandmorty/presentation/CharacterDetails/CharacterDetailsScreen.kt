package com.dentonstudio.rickandmorty.presentation.CharacterDetails

import android.widget.Space
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.dentonstudio.rickandmorty.presentation.component.EpisodeRowItem
import com.dentonstudio.rickandmorty.util.GifScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterDetailsScreen(
    viewModel: CharacterDetailsViewModel = hiltViewModel(),
    navController: NavController
) {
    val character = viewModel.details.value.charaterDetails
    val episode = viewModel.stateEpisode.value.episodes



    LazyColumn(modifier = Modifier
        .background(Color(0xFF272B33))
        .padding(5.dp)) {
        item { Spacer(modifier = Modifier.height(10.dp)) }
        item {
            character?.name?.let {
                Text(
                    text = it,
                    style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
                    color = Color(0xFF93B897)

                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    if (character.status.equals("Alive")) {
                        Box(
                            modifier = Modifier
                                .size(5.dp)
                                .clip(CircleShape)
                                .background(Color.Green)
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .size(5.dp)
                                .clip(CircleShape)
                                .background(Color.Red)
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = character.status,
                        color = Color.White,
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        )
                    )
                    Text(
                        text = " - ",
                        color = Color.White,
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        )
                    )
                    Text(
                        text = character.species,
                        color = Color.White,
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        )
                    )

                }
            }
        }
        item {  if(viewModel.details.value.isLoading || viewModel.stateEpisode.value.isLoading){

            GifScreen()
        } }
        item {
            AsyncImage(
                model = character?.image, contentDescription = "", modifier = Modifier
                    .height(400.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    )
            )
        }

        episode.groupBy { it.seasonNumber }.forEach { mapEntry ->
            item { Spacer(modifier = Modifier.height(10.dp)) }
            stickyHeader { seasonNumber(mapEntry.key) }
            item { Spacer(modifier = Modifier.height(10.dp)) }

            items(mapEntry.value.size){
                Spacer(modifier = Modifier.height(5.dp))
                EpisodeRowItem(episode = mapEntry.value.get(it))
                Spacer(modifier = Modifier.height(5.dp))
            }
        }

    }


}

@Composable
fun seasonNumber(seasonId: Int) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2E2D2D))
            .border(
                width = 2.dp,
                color = Color.White,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(20.dp),
        text = "Season ${seasonId}",
        color = Color.White,
        style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
        textAlign = TextAlign.Center
    )


}