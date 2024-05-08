package com.dentonstudio.rickandmorty.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.dentonstudio.rickandmorty.domain.model.Result
import kotlin.math.round

@Composable
fun ItemScreen(charachter: Result) {
    Box (modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(15.dp))
        .background(Color(0xFF3C3E44))){
        Row (modifier = Modifier.fillMaxSize(),
            ){
            Box (modifier = Modifier
            ){

                AsyncImage(model =charachter.image , contentDescription = "", modifier = Modifier
                    .clip(RoundedCornerShape(15.dp)
                ))

            }
            Spacer(modifier = Modifier.width(20.dp))
            Box (modifier = Modifier){
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(text = charachter.name , color = Color.White, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
                    Spacer(modifier = Modifier.height(5.dp))
                    Row( horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {

                        if(charachter.status.equals("Alive")){ Box(modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(Color.Green))
                        }else{
                            Box(modifier = Modifier
                                .size(5.dp)
                                .clip(CircleShape)
                                .background(Color.Red))
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = charachter.status , color = Color.White, style = TextStyle(fontWeight = FontWeight.Light, fontSize = 10.sp))
                        Text(text = " - " , color = Color.White, style = TextStyle(fontWeight = FontWeight.Light, fontSize = 10.sp))
                        Text(text = charachter.species , color = Color.White, style = TextStyle(fontWeight = FontWeight.Light, fontSize = 10.sp))

                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Column {
                        Text(text = "Last known location:" , color = Color(0xFF758492), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 10.sp))
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = charachter.location.name , color = Color.White, style = TextStyle(fontWeight = FontWeight.Light, fontSize = 15.sp))
                    }


                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun item() {
    Box (modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(5.dp))){
        Row (

            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Box (modifier = Modifier
                .fillMaxWidth(.3f)

                .background(Color.Green)){
                Text(text = "1st side")
            }
            Box (modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)){
               Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "name")
                   Spacer(modifier = Modifier.height(8.dp))

               }
            }

        }
    }
}

