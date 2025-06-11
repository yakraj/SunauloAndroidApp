package com.sunaulo.sunauloapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.sunaulo.sunauloapp.components.PostCard
import androidx.compose.ui.res.painterResource
import com.sunaulo.sunauloapp.R // Assuming R is accessible for resources
import androidx.navigation.NavHostController
import com.sunaulo.sunauloapp.Screen // Import the Screen sealed class

@Composable
fun HomeScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Loop 10 times to display PostCard
        repeat(10) { index ->
            val postId = "post_${index + 1}"
            PostCard(
                userName = "Jaya thapa",
                price = "500",
                description = "important conversation about the life is going why so long. this is going to be very frustrating",
                postImage = painterResource(id = R.drawable.ic_launcher_foreground), // Using a default drawable, replace with your image resource
                likesCount = "287.698",
                viewsCount = "500",
                onImageClick = { /* TODO: Implement image view logic */ },
                onDescriptionClick = { navController.navigate(Screen.PostDetails.route.replace("{${Screen.PostDetails.POST_ID_KEY}}", postId)) }
            )
            // Add some space between cards
            //Spacer(modifier = Modifier.height(8.dp))
        }
    }
}