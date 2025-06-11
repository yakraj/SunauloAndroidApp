package com.sunaulo.sunauloapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.navigation.compose.rememberNavController
import com.sunaulo.sunauloapp.Screen
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable

data class Chat(val id: Int, val name: String, val lastMessage: String, val time: String, val imageUrl: Int)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ChatListScreen(navController: NavHostController) {
    var selectedChats by remember { mutableStateOf(setOf<Int>()) }
    var showOptionsMenu by remember { mutableStateOf(false) }
    var selectedChatForOptions by remember { mutableStateOf<Chat?>(null) }

    val chats = listOf(
        Chat(1, "Jaya thapa", "Hi dear... I just want to know...", "10 min ago", R.drawable.placeholder_profile_image),
        Chat(2, "Jaya thapa", "Hi dear... I just want to know...", "10 min ago", R.drawable.placeholder_profile_image),
        Chat(3, "Jaya thapa", "Hi dear... I just want to know...", "10 min ago", R.drawable.placeholder_profile_image),
        Chat(4, "Jaya thapa", "Hi dear... I just want to know...", "10 min ago", R.drawable.placeholder_profile_image)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (selectedChats.isNotEmpty()) {
                IconButton(onClick = { selectedChats = emptySet() }) {
                    Icon(Icons.Default.Close, contentDescription = "Cancel Selection")
                }
                Text(
                    "${selectedChats.size} selected",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                if (selectedChats.size == 1) {
                    IconButton(onClick = { /* TODO: Show info */ }) {
                        Icon(Icons.Default.Info, contentDescription = "Info")
                    }
                    IconButton(onClick = { /* TODO: Edit chat */ }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                }
                IconButton(onClick = { /* TODO: Delete selected chats */ }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            } else {
            IconButton(onClick = { /* TODO: Handle back navigation */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Chats", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Chat List
        LazyColumn {
            items(chats) { chat ->
                ChatListItem(
                    chat = chat,
                    isSelected = selectedChats.contains(chat.id),
                    onChatClick = {
                        if (selectedChats.isNotEmpty()) {
                            selectedChats = if (selectedChats.contains(chat.id)) {
                                selectedChats - chat.id
                            } else {
                                selectedChats + chat.id
                            }
                        } else {
                            navController.navigate(Screen.ChatDetail.route.replace("{${Screen.ChatDetail.CHAT_ID_KEY}}", chat.id.toString()))
                        }
                    },
                    onChatLongClick = {
                        if (selectedChats.isEmpty()) {
                            selectedChats = setOf(chat.id)
                        } else {
                            selectedChats = selectedChats + chat.id
                        }
                    },
                    onOptionsClick = {
                        selectedChatForOptions = chat
                        showOptionsMenu = true
                    }
                )
            }
        }
    }

    if (showOptionsMenu && selectedChatForOptions != null) {
        AlertDialog(
            onDismissRequest = { showOptionsMenu = false },
            title = { Text("Chat Options") },
            text = {
                Column {
                    TextButton(
                        onClick = {
                            showOptionsMenu = false
                            // TODO: Show chat info
                        }
                    ) {
                        Icon(Icons.Default.Info, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("View Info")
            }
                    TextButton(
                        onClick = {
                            showOptionsMenu = false
                            // TODO: Edit chat
                        }
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Edit Chat")
                    }
                    TextButton(
                        onClick = {
                            showOptionsMenu = false
                            // TODO: Delete chat
                        }
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Delete Chat")
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showOptionsMenu = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun ChatListItem(
    chat: Chat,
    isSelected: Boolean,
    onChatClick: () -> Unit,
    onChatLongClick: () -> Unit,
    onOptionsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isSelected) Color(0xFFE3F2FD) else Color.Transparent)
            .combinedClickable(
                onClick = onChatClick,
                onLongClick = onChatLongClick
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        
        // Profile Image
        Image(
            painter = painterResource(id = chat.imageUrl),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray, CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        // Chat Info
        Column(modifier = Modifier.weight(1f)) {
            Text(chat.name, fontWeight = FontWeight.Bold)
            Text(chat.lastMessage, fontSize = 14.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.width(8.dp))
        // Time and Options
        Column(horizontalAlignment = Alignment.End) {
            Text(chat.time, fontSize = 12.sp, color = Color.Gray)
            if (!isSelected) {
                IconButton(onClick = onOptionsClick) {
                Icon(Icons.Default.MoreVert, contentDescription = "Options")
                }
            }
        }
    }
    Divider(modifier = Modifier.padding(horizontal = 16.dp))
}

@Preview(showBackground = true)
@Composable
fun PreviewChatListScreen() {
    ChatListScreen(navController = rememberNavController())
} 