package com.dentonstudio.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dentonstudio.rickandmorty.presentation.CharacterDetails.CharacterDetailsScreen
import com.dentonstudio.rickandmorty.presentation.CharacterScreen
import com.dentonstudio.rickandmorty.presentation.component.HomeViewModel
import com.dentonstudio.rickandmorty.ui.theme.RickandMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var viewModel: HomeViewModel
        setContent {
            RickandMortyTheme {

                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "Home") {
                        composable(route = "Home") {
                            CharacterScreen(
                                navController = navController
                            )
                        }

                        composable(route = "CharacterDetails"+
                                "?CharacterId={CharacterId}",
                            arguments = listOf(
                                navArgument(
                                    name = "CharacterId"
                                ){
                                    type  = NavType.IntType
                                    defaultValue = -1
                                }
                            )) {
                            CharacterDetailsScreen( navController = navController)
                        }
                    }


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RickandMortyTheme {
        Greeting("Android")
    }
}
