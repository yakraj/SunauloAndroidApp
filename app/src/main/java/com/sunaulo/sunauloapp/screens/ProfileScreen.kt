package com.sunaulo.sunauloapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunaulo.sunauloapp.R
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.clickable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import androidx.compose.ui.window.PopupProperties
import com.sunaulo.sunauloapp.components.PostCard

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun ProfileScreen() {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf("Active", "Sold")
    var showMenu by remember { mutableStateOf(false) }

    val activePosts = remember { listOf(
        Post(1, "Jaya thapa", "500", "important conversation about the life is going why so long. this is going to be very frustrating", R.drawable.placeholder_profile_image, "287.698", "500"),
        Post(2, "Jaya thapa", "600", "Another active post description.", R.drawable.placeholder_profile_image, "150", "300"),
    )}
    val soldPosts = remember { listOf(
        Post(3, "Jaya thapa", "400", "A sold post description.", R.drawable.placeholder_profile_image, "100", "200"),
    )}

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                IconButton(onClick = { showMenu = true }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("About App") },
                        onClick = { /* TODO: Navigate to About App */ showMenu = false }
                    )
                    DropdownMenuItem(
                        text = { Text("Share") },
                        onClick = { /* TODO: Share profile */ showMenu = false }
                    )
                    DropdownMenuItem(
                        text = { Text("Activity") },
                        onClick = { /* TODO: View activity */ showMenu = false }
                    )
                    DropdownMenuItem(
                        text = { Text("Your Likes") },
                        onClick = { /* TODO: View likes */ showMenu = false }
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.placeholder_profile_image),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Jaya thapa", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("jaya123", fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Star, contentDescription = "Stars", tint = Color(0xFFFFA726))
                Text("500", fontSize = 16.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.LocationOn, contentDescription = "Location", tint = Color.Gray)
                Text("sanothimi bhaktapur, kathmandu", fontSize = 16.sp, color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = { /* TODO: Claim Stars */ }) {
                    Text("Claim Stars • Buy")
                }
                Button(onClick = { /* TODO: Claimed Stars */ }) {
                    Text("Claimed Stars")
                }
            }
        }

        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(title) }
                )
            }
        }

        HorizontalPager(count = tabs.size, state = pagerState) {
            page ->
            LazyColumn {
                when (page) {
                    0 -> {
                        items(activePosts) { post ->
                            PostCard(
                                userName = post.userName,
                                price = post.price,
                                description = post.description,
                                postImage = painterResource(id = post.postImageResource),
                                likesCount = post.likesCount,
                                viewsCount = post.viewsCount,
                                onImageClick = { /* TODO: Handle image click */ },
                                onDescriptionClick = { /* TODO: Handle description click */ }
                            )
                        }
                    }
                    1 -> {
                        items(soldPosts) { post ->
                            PostCard(
                                userName = post.userName,
                                price = post.price,
                                description = post.description,
                                postImage = painterResource(id = post.postImageResource),
                                likesCount = post.likesCount,
                                viewsCount = post.viewsCount,
                                onImageClick = { /* TODO: Handle image click */ },
                                onDescriptionClick = { /* TODO: Handle description click */ }
                            )
                        }
                    }
                }
            }
        }
    }
}

data class Post(
    val id: Int,
    val userName: String,
    val price: String,
    val description: String,
    val postImageResource: Int,
    val likesCount: String,
    val viewsCount: String
)

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
} 