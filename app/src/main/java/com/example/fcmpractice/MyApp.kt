package com.example.fcmpractice

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyApp(startDestination: String) {
    val navController = rememberNavController()

    val activity = LocalActivity.current as Activity
    val target = activity.intent?.getStringExtra("target")


    LaunchedEffect(target) {
        if(!target.isNullOrEmpty()) {
            navController.navigate(target) {
                popUpTo(0)
            }
        }
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable("home") {
            HomeScreen(navController)
        }

        composable("fav") {
            FavScreen(navController)
        }

        composable("profile") {
            ProfileScreen(navController)
        }

        composable("cart") {
            CartScreen(navController)
        }

        composable("admin") {
            AdminSendScreen()
        }
    }
}