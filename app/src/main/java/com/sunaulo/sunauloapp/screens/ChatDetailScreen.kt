package com.sunaulo.sunauloapp.screens

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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunaulo.sunauloapp.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.window.PopupProperties
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable

data class Message(val id: Int, val text: String, val time: String, val isUser: Boolean)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ChatDetailScreen(chatId: Int?) {
    var selectedMessages by remember { mutableStateOf(setOf<Int>()) }
    var showTopBarOptions by remember { mutableStateOf(false) }
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    val messages = listOf(
        Message(1, "hello", "29:30 AM", true),
        Message(2, "Hi", "29:30 AM", false),
        Message(3, "$ 500\nimportant\nconversation about\nthe Life is going why\nso long.", "29:30 AM", false)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (selectedMessages.isNotEmpty()) {
                IconButton(onClick = { selectedMessages = emptySet() }) {
                    Icon(Icons.Default.Close, contentDescription = "Cancel Selection")
                }
                Text(
                    "${selectedMessages.size} selected",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                if (selectedMessages.size == 1) {
                    IconButton(onClick = { /* TODO: Forward message */ }) {
                        Icon(Icons.Default.Share, contentDescription = "Forward")
                    }
                    IconButton(onClick = { /* TODO: Copy message */ }) {
                        Icon(Icons.Default.ContentCopy, contentDescription = "Copy")
                    }
                }
                IconButton(onClick = { /* TODO: Delete selected messages */ }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            } else {
            IconButton(onClick = { /* TODO: Handle back navigation */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(8.dp))
            // Profile Image and Name
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                ) { /* Placeholder for profile image */ }
                Spacer(modifier = Modifier.width(8.dp))
                Text("Jaya thapa", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.weight(1f))
                Box {
                    IconButton(onClick = { showTopBarOptions = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Options")
                    }
                    DropdownMenu(
                        expanded = showTopBarOptions,
                        onDismissRequest = { showTopBarOptions = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        DropdownMenuItem(
                            text = { Text("See Profile") },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = null,
                                    tint = Color(0xFF6200EE)
                                )
                            },
                            onClick = {
                                showTopBarOptions = false
                                // TODO: Navigate to profile
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Block User") },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Block,
                                    contentDescription = null,
                                    tint = Color.Red
                                )
                            },
                            onClick = {
                                showTopBarOptions = false
                                // TODO: Show block confirmation dialog
                            }
                        )
                    }
                }
            }
        }

        // Message List
        LazyColumn(
            modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
            reverseLayout = true
        ) {
            items(messages.reversed()) { message ->
                MessageItem(
                    message = message,
                    isSelected = selectedMessages.contains(message.id),
                    onMessageClick = {
                        if (selectedMessages.isNotEmpty()) {
                            selectedMessages = if (selectedMessages.contains(message.id)) {
                                selectedMessages - message.id
                            } else {
                                selectedMessages + message.id
                            }
                        } else {
                            // TODO: Handle default single tap behavior (e.g., show message details), currently does nothing
                        }
                    },
                    onMessageLongClick = {
                        if (selectedMessages.isEmpty()) {
                            selectedMessages = setOf(message.id)
                        } else {
                            selectedMessages = selectedMessages + message.id
                        }
                    }
                )
            }
        }

        // Message Input
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = textState,
                onValueChange = { textState = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("hi, How are you doing") },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = { /* TODO: Send message */ }) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF6200EE)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Send, contentDescription = "Send Message", tint = Color.White)
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun MessageItem(
    message: Message,
    isSelected: Boolean,
    onMessageClick: () -> Unit,
    onMessageLongClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = if (isSelected) Color(0xFFE0F7FA) else Color.Transparent)
            .padding(vertical = 4.dp),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = if (message.isUser) Color(0xFFE0E0E0) else Color(0xFF6200EE),
                    shape = RoundedCornerShape(8.dp)
                )
                .combinedClickable(
                    onClick = onMessageClick,
                    onLongClick = onMessageLongClick
                )
                .padding(8.dp)
        ) {
            Column {
                Text(message.text, color = if (message.isUser) Color.Black else Color.White)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                Text(message.time, fontSize = 10.sp, color = if (message.isUser) Color.Gray else Color(0xFFEEEEEE))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatDetailScreen() {
    ChatDetailScreen(null)
} 