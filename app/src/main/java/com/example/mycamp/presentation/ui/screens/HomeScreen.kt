package com.example.mycamp.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycamp.R
import com.example.mycamp.presentation.ui.theme.*

@Composable
fun HomeScreen() {
    Column {
        HomeHeader()
        Spacer(modifier = Modifier.height(4.dp))
        SearchSection(modifier = Modifier.fillMaxWidth(),onValueChanged = {})
        Spacer(modifier = Modifier.height(12.dp))
        MainContent(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun HomeHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = (R.drawable.avatar_img)), contentDescription = "avatar Image", modifier = Modifier.size(36.dp))
        Text(text = "Welcome to My Camp", fontSize = 16.sp, fontFamily = robotoFamily, fontWeight = FontWeight.Bold)
        Box(modifier = Modifier
            .size(36.dp)
            .shadow(3.dp, CircleShape)
            .clip(CircleShape),
        contentAlignment = Alignment.Center)
        {
            Icon(painter = painterResource(id = R.drawable.ic_notification), contentDescription = "Notification", modifier = Modifier.size(18.dp))
        }
    }
}

@Composable
fun SearchSection(
    onValueChanged:(String) -> Unit,
    modifier: Modifier = Modifier
){
    var searchVal by remember {
        mutableStateOf( TextFieldValue(""))
    }
    Column(modifier = modifier
        .height(220.dp)
        .padding(horizontal = 40.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = "Search", Modifier.size(24.dp))
                    TextField(
                        modifier = Modifier.width(120.dp),
                        value = searchVal,
                        onValueChange = {
                            searchVal = it
                            onValueChanged(searchVal.text)
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            disabledTextColor = Color.Transparent,
                            backgroundColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        placeholder = { Text(text = "Location", fontFamily = robotoFamily, fontWeight = FontWeight.Normal, fontSize = 16.sp) },
                    )
                }
            }
            Icon(painter = painterResource(id = R.drawable.ic_explore_location), contentDescription = "search", Modifier.size(24.dp))
        }
        Row(
            modifier = modifier.height(60.dp),
            verticalAlignment = Alignment.CenterVertically){
            Icon(painter = painterResource(id = R.drawable.ic_calendar), contentDescription = "Calendar", modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "July 09 - July 15", modifier = Modifier.clickable { }, fontSize = 16.sp, fontFamily = robotoFamily, fontWeight = FontWeight.Normal)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.height(60.dp),
            verticalAlignment = Alignment.CenterVertically){
            Box {
                Row {
                    Icon(painter = painterResource(id = (R.drawable.ic_people)), contentDescription = "Guest Quantity", Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "2 Guests")
                }
            }
            Box {
                Row (verticalAlignment = Alignment.CenterVertically){
                    Icon(painter = painterResource(id = R.drawable.ic_remove), contentDescription = "remove", modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Divider(modifier = Modifier
                        .height(25.dp)
                        .width(1.dp), thickness = 1.dp, color = LightGreyColor)
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "remove", modifier = Modifier.size(16.dp))
                }
            }
        }
        var buttonColor by remember {
            mutableStateOf(DarkGrayColor)
        }
        Box(contentAlignment = Alignment.Center, modifier = modifier) {
            Button(
                onClick = {
                          buttonColor = AquaSelectedColor
                },
                modifier = Modifier
                    .width(140.dp)
                    .height(40.dp)
                    .shadow(4.dp, CircleShape)
                    .padding(bottom = 5.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonColor
                ),
                shape = CircleShape
            ) {
                Text(text = "Search", color = Color.White, fontSize = 14.sp, fontFamily = robotoFamily, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    LazyColumn{
        items(5){
            ContentItem(modifier)
        }
    }
}

@Composable
fun ContentItem(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        DarkAquaColor.copy(alpha = 0.8f),
                        LightAquaColor,
                    )
                )
            )
            .height(450.dp)
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.content_img),
            contentDescription = "Image",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(25.dp))
                .padding(bottom = 20.dp),
            contentScale = ContentScale.Crop
        )
        Text(text = "DETAILS", fontSize = 14.sp, color = Color.Black, fontWeight = FontWeight.Medium, fontFamily = robotoFamily, letterSpacing = 0.1.sp, lineHeight = 20.sp)
        Text(text = "There is no other place like Bali in this world. A magical mix of culture, people, nature, activities, weather, culinary delights, nightlife and many other interesting things that can make your stay unforgettable.",
            color = LightGreyColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.height(100.dp),
            overflow = TextOverflow.Ellipsis,
            fontFamily = robotoFamily
        )
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)) {
            Button(
                onClick = {},
                modifier = Modifier
                    .width(140.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = DarkGrayColor.copy(alpha = 0.6f)
                ),
                shape = CircleShape
            ){
                Text(text = "Learn More", color = Color.Black, fontSize = 14.sp, fontFamily = robotoFamily, fontWeight = FontWeight.Medium)
            }
            Button(
                onClick = {},
                modifier = Modifier
                    .width(140.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = DarkGrayColor.copy(alpha = 0.6f)
                ),
                shape = CircleShape
            ){
                Text(text = "Reserve", color = Color.Black, fontSize = 14.sp, fontFamily = robotoFamily, fontWeight = FontWeight.Medium)
            }
        }
    }
}