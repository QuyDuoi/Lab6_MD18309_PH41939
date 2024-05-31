package com.quyduoi.lab6_md18309_ph41939

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.quyduoi.lab6_md18309_ph41939.giao_dien.screens.CinemaSeatBookingScreen
import com.quyduoi.lab6_md18309_ph41939.giao_dien.screens.createTheaterSeating
import com.quyduoi.lab6_md18309_ph41939.ui.theme.Lab6_MD18309_PH41939Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab6_MD18309_PH41939Theme {
                MyApp()
            }
        }
    }
}

@Preview
@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home_screen") {
        composable("home_screen") {
            HomeScreen(navController)
        }
        composable("screen1") {
            Bai1(Movie.getSampleMovies())
        }
        composable("screen3") {
            CinemaSeatBookingScreen(
                createTheaterSeating(
                    totalRows = 12,
                    totalSeatsPerRow = 9,
                    aislePositionInRow = 4,
                    aislePositionInColumn = 5
                ), totalSeatsPerRow = 9
            )
        }
    }
}
@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Lab 6 - MD18309 - PH41939", fontSize = 25.sp,
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            navController.navigate("screen1")
        }, modifier = Modifier
            , shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Text("Bài 1 và Bài 2")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            navController.navigate("screen3")
        }, modifier = Modifier
            , shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Text("Bài 3")
        }
    }
}