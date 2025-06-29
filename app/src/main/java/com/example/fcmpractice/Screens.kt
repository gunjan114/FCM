package com.example.fcmpractice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    ScreenContent(name = "Home", navController = navController)
}

@Composable
fun FavScreen(navController: NavController) {
    ScreenContent(name = "Fav", navController = navController)
}

@Composable
fun ProfileScreen(navController: NavController) {
    ScreenContent(name = "Profile", navController = navController)
}

@Composable
fun CartScreen(navController: NavController) {
    ScreenContent(name = "Cart", navController = navController)
}

@Composable
fun ScreenContent(name: String, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("$name Screen")
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate("home")
            }
        ) {
            Text("Go to Home")
        }

        Button(
            onClick = {
                navController.navigate("admin")
            }
        ) {
            Text("Admin Panel")
        }
    }
}