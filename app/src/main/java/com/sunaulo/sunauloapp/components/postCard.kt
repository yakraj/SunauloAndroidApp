package com.sunaulo.sunauloapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.*
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
import com.sunaulo.sunauloapp.R // Assuming you have drawable resources
import androidx.compose.foundation.clickable

@Composable
fun PostCard(
    userName: String,
    price: String,
    description: String,
    postImage: Painter,
    likesCount: String,
    viewsCount: String,
    onImageClick: () -> Unit,
    onDescriptionClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.Gray) // Optional: Add a border to visualize the card
            .padding(8.dp)
    ) {
        // Header (Profile Pic, Name, Time/Price, See More)
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
                    Text(userName, fontWeight = FontWeight.Bold)
                    Text("$" + price, fontSize = 12.sp, color = Color.Gray)
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                 Text("7 days ago", fontSize = 12.sp, color = Color.Gray)
                 Text("see more...", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Description
        Text(
            text = description,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable { onDescriptionClick() }
        )

        // Post Image
        Image(
            painter = postImage,
            contentDescription = "Post Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                .clickable { onImageClick() },
            contentScale = ContentScale.Crop
        )

        // Footer (Likes, Chat, Share, Views)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = "Like")
                Text(likesCount, modifier = Modifier.padding(start = 4.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Icon(Icons.Outlined.ChatBubbleOutline, contentDescription = "Chat")
                Text("Chat", modifier = Modifier.padding(start = 4.dp))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Send, contentDescription = "Share")
                Spacer(modifier = Modifier.width(16.dp))
                Text(viewsCount + " views", fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

// @Preview(showBackground = true)
// @Composable
// fun PreviewPostCard() {
//     PostCard(
//         userName = "Jaya thapa",
//         price = "500",
//         description = "important conversation about the life is going why so long. this is going to be very frustrating",
//         postImage = painterResource(id = R.drawable.placeholder_image), // Replace with a placeholder drawable resource
//         likesCount = "287.698",
//         viewsCount = "500"
//     )
// } 