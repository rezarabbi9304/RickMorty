package com.dentonstudio.rickandmorty.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dentonstudio.rickandmorty.domain.model.Episode

@Composable
fun EpisodeRowItem(
    episode: Episode
) {
    Row (modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween){
        Column() {
            Text(text = "episode", color = Color.White)
            Text(text = episode.episodeNumber.toString(), color = Color.White,style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.SemiBold))

        }

        Column(horizontalAlignment = Alignment.End) {
            Text(text = episode.name.trim(), color = Color.White , style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold))
            Text(text = episode.air_date.trim(), color = Color.White)
        }
    }
}