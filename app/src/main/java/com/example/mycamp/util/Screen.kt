package com.example.mycamp.util

sealed class Screen(val route:String){
    object HomeScreen: Screen("home_screen")
    object SearchScreen: Screen("search_screen")
    object ChatScreen: Screen("chat_screen")
    object ProfileScreen: Screen("profile_screen")
}
