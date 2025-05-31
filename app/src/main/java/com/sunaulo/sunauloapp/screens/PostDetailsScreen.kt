package com.sunaulo.sunauloapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunaulo.sunauloapp.R // Assuming R is accessible for resources
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun PostDetailsScreen(postId: String?) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Header (Profile Pic, Name, Time/Price, Share)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Profile Picture Placeholder
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("Jaya thapa", fontWeight = FontWeight.Bold)
                    Text("$500", fontSize = 12.sp, color = Color.Gray)
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Send, contentDescription = "Share")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Share", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
                 Text("7 days ago", fontSize = 12.sp, color = Color.Gray)
            }
        }

        // Full Description
        Text(
            text = "important conversation about the life is going why so long. this is going to be very frustrating. I never we would end this service with this kind of moto but it happend.\n1. i don't want anything\n2. this is not my issue\n3. who did this\n4. there is no politics\n5. I never involved here\nI hope you will understand my feeling what i want to say to you and tell all of the peoples. Guys are doing good with small position but i'm not able to do means that is not like, I don't have gut. I have much but not able to do. Let's see where life takes . I'll keep posting about my life. consider to follow me as well.",
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Post Image
         Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Using a default drawable, replace with your image resource
            contentDescription = "Post Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // Adjust height for a single post view
                .clip(shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        // Footer (Likes, Chat, Views)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = "Like")
                Text("287.698 Likes", modifier = Modifier.padding(start = 4.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Icon(Icons.Outlined.ChatBubbleOutline, contentDescription = "Chat")
                Text("Chat", modifier = Modifier.padding(start = 4.dp))
            }
            Text("500 views", fontSize = 12.sp, color = Color.Gray)
        }
        // The Post ID is available via the postId parameter if needed for data fetching
        // postId?.let { Text("Debug: Post ID - $it") }
    }
} 