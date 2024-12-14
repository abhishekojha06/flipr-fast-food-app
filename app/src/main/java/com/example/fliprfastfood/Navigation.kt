package com.example.fliprfastfood

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fliprfastfood.Pages.Home
import com.example.fliprfastfood.Pages.ItemDetailPage

import com.example.fliprfastfood.Pages.Login

import com.example.fliprfastfood.Pages.Signup
import com.example.fliprfastfood.Pages.WelcomeScreen
import com.example.fliprfastfood.ViewModel.AuthViewModel


sealed class Screen(val route: String) {
    object Welcome : Screen("Welcome")
    object Login : Screen("Login")
    object Signup : Screen("Signup")
    object Home : Screen("Home")
    object Profile : Screen("Profile")
    object ItemDetailPage : Screen("ItemDetailPage")
}


@Composable
fun MyAppNavigation(modifier: Modifier = Modifier,authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Welcome.route, builder = {

        composable(Screen.Welcome.route){
            WelcomeScreen(modifier,navController, authViewModel)
        }
        composable(Screen.Login.route){
            Login(modifier,navController,authViewModel)
        }
        composable(Screen.Signup.route){
            Signup(modifier,navController,authViewModel)
        }
        composable(Screen.Home.route){
            Home()
        }

        composable(Screen.Profile.route){
            ProfilePage(modifier,authViewModel,navController)
        }

        composable(Screen.ItemDetailPage.route){
            ItemDetailPage(itemName = "Flipr Innovation labs", description = "Abhishek ojha", originalPrice = 100, discountedPrice = 120, packageCost = 10, onBackClick = { /* Handle back click */ }, onAddToOrderClick = 11, modifier = Modifier, navController)
        }
      





    })
}