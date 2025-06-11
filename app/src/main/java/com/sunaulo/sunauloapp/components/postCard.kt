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
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.IconButton
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.remember
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.filled.MarkChatUnread

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
    ) {Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .padding(vertical = 5.dp)
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

            }
        }

        // Description
        Text(
            text = description,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable { onDescriptionClick() }
        )
        Row(modifier = Modifier.fillMaxWidth().offset(y = -15.dp), horizontalArrangement = Arrangement.End) {
            Text("see more...", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
        }

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
        Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,) {
            Text(likesCount + " likes", fontSize = 12.sp, color = Color.Gray)
            Text(viewsCount + " views", fontSize = 12.sp, color = Color.Gray)

        }
        // Footer (Likes, Chat, Share, Views)
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                Row {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(30.dp) // Set the size of the circular area
                                .clip(CircleShape) // Clip the Box to a circular shape
                                .clickable(
                                    onClick = { /* TODO: Handle click action */ },
                                    indication = rememberRipple(bounded = true), // Circular ripple
                                    interactionSource = remember { MutableInteractionSource() }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "Like",
                                tint = Color.Gray
                            )

                        }
                        Text("Like", fontSize = 12.sp, color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally)  {
                        Box(
                            modifier = Modifier
                                .size(30.dp) // Set the size of the circular area
                                .clip(CircleShape) // Clip the Box to a circular shape
                                .clickable(
                                    onClick = { /* TODO: Handle click action */ },
                                    indication = rememberRipple(bounded = true), // Circular ripple
                                    interactionSource = remember { MutableInteractionSource() }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.MarkChatUnread,
                                contentDescription = "Like",
                                tint = Color.Gray
                            )

                        }
                        Text("Chat", fontSize = 12.sp, color = Color.Gray)
                    }


               }
                Column {
                    Box(
                        modifier = Modifier
                            .size(30.dp) // Set the size of the circular area
                            .clip(CircleShape) // Clip the Box to a circular shape
                            .clickable(
                                onClick = { /* TODO: Handle click action */ },
                                indication = rememberRipple(bounded = true), // Circular ripple
                                interactionSource = remember { MutableInteractionSource() }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Like",
                            tint = Color.Gray
                        )

                    }
                    Text("Share", fontSize = 12.sp, color = Color.Gray)
                }

                 }


        }


    }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
//            .alpha(0.5f)
                .background(Color.Gray)
        )
    }


}

 @Preview(showBackground = true)
 @Composable
 fun PreviewPostCard() {
     PostCard(
         userName = "Jaya thapa",
         price = "500",
         description = "important conversation about the life is going why so long. this is going to be very frustrating",
         postImage = painterResource(id = R.drawable.my_post_image), // Replace with a placeholder drawable resource
         likesCount = "287.698",
         viewsCount = "500",
         onImageClick = { /* TODO: Handle image click */ },
         onDescriptionClick = { /* TODO: Handle description click */ }


     )
 }