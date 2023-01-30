package com.example.mycamp.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycamp.R
import com.example.mycamp.presentation.ui.theme.DarkGrayColor
import com.example.mycamp.presentation.ui.theme.AquaSelectedColor
import com.example.mycamp.util.MenuItem
import com.example.mycamp.util.Screen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    BottomSheetScaffold(
            sheetShape = CircleShape,
            sheetBackgroundColor = Color.Transparent,
            sheetContent = {
                BottomNavBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(30.dp)),
                    navigationController = navController,
                    items = listOf(
                        MenuItem(route = Screen.HomeScreen.route, painterResource(id = R.drawable.ic_home)),
                        MenuItem(route = Screen.SearchScreen.route, painterResource(id = R.drawable.ic_search)),
                        MenuItem(route = Screen.ChatScreen.route, painterResource(id = R.drawable.ic_location_point)),
                        MenuItem(route = Screen.ProfileScreen.route, painterResource(id = R.drawable.ic_profile))
                    ),
                    onItemClick = {
                        navController.navigate(it.route)
                    })
            },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp
    ) {
        NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
            composable(route = Screen.HomeScreen.route){
                HomeScreen()
            }
            composable(route = Screen.SearchScreen.route){
                HomeScreen()
                println("Search")
            }
            composable(route = Screen.ChatScreen.route){
                HomeScreen()
            }
            composable(route = Screen.ProfileScreen.route){
                HomeScreen()
            }
        }
    }
}

@Composable
fun BottomNavBar(
    navigationController: NavHostController,
    modifier: Modifier = Modifier,
    items:List<MenuItem>,
    onItemClick: (MenuItem) -> Unit
) {
    val backStackEntry = navigationController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = DarkGrayColor
    ) {
        items.forEach { item->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                modifier = Modifier
                    .padding(bottom = if(selected) 5.dp else 0.dp),
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = AquaSelectedColor,
                unselectedContentColor = Color.White,
                icon = {
                    Box(modifier = Modifier
                        .size(40.dp)
                        .background(
                            if (selected) Color.White else Color.Transparent,
                            shape = CircleShape
                        ),
                        contentAlignment = Alignment.Center
                        ){
                        Icon(painter = item.icon, contentDescription = item.route, modifier = Modifier.size(20.dp))
                    }
                }
            )
        }
    }
}

