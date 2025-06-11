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
import androidx.compose.ui.platform.LocalDensity
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun ProfileScreen() {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf("Active", "Sold")
    var showMenu by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val density = LocalDensity.current

    val activePosts = remember { listOf(
        Post(1, "Jaya thapa", "500", "important conversation about the life is going why so long. this is going to be very frustrating", R.drawable.placeholder_profile_image, "287.698", "500"),
        Post(2, "Jaya thapa", "600", "Another active post description.", R.drawable.placeholder_profile_image, "150", "300"),
    )}
    val soldPosts = remember { listOf(
        Post(3, "Jaya thapa", "400", "A sold post description.", R.drawable.placeholder_profile_image, "100", "200"),
    )}

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Profile Header Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
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
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
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
                    Column() {
                        Text("Jaya thapa", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Text("jaya123", fontSize = 16.sp, color = Color.Gray)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("500", fontSize = 16.sp, color = Color.Gray)
                        Icon(Icons.Default.Star, contentDescription = "Stars", tint = Color(0xFFFFA726))
                        Icon(Icons.Default.Star, contentDescription = "Stars", tint = Color(0xFFFFA726))
                        Icon(Icons.Default.Star, contentDescription = "Stars", tint = Color(0xFFFFA726))
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("sanothimi bhaktapur, kathmandu bhanda ne tadha, kata ho kata. harauxau timi.", fontSize = 16.sp, color = Color.Blue)
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }

            // Stats Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Claim Stars • Buy", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(5.dp))
                    Row {
                        Text("• Khalti", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Blue)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("• E-Sewa", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Green)
                    }
                }
                Column(
                    modifier = Modifier
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Claimed Stars", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(5.dp))
                    Row {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Stars",
                            tint = Color(0xFFFFA726),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("600", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFFFFA726))
                    }
                }
            }

            // Tab Row
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = { Text(title) }
                    )
                }
            }

            // Content Section
            HorizontalPager(
                count = tabs.size,
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                Column {
                    when (page) {
                        0 -> {
                            activePosts.forEach { post ->
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
                            soldPosts.forEach { post ->
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

        // Sticky Tab Row
        val headerHeight = with(density) { 300.dp.toPx() }
        val shouldShowStickyTab = scrollState.value > headerHeight

        if (shouldShowStickyTab) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .align(Alignment.TopCenter)
            ) {
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                            text = { Text(title) }
                        )
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