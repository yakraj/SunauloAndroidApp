package com.sunaulo.sunauloapp.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.pager.HorizontalPagerIndicator
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.border
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun AddScreen(navController: NavController) {
    val context = LocalContext.current
    var selectedImageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }
    var isInitialLaunch by remember { mutableStateOf(true) }
    var selectedTabIndex by remember { mutableStateOf(0) }
    var descriptionText by remember { mutableStateOf("") }
    var priceText by remember { mutableStateOf("") }
    var conditionText by remember { mutableStateOf("new") }
    val conditions = listOf("new", "used-like new", "used-good", "used-fair")
    var expanded by remember { mutableStateOf(false) }
    val pagerState = rememberPagerState()

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris ->
            val currentSize = selectedImageUris.size
            val remainingSlots = 5 - currentSize
            if (remainingSlots > 0) {
                selectedImageUris = selectedImageUris + uris.take(remainingSlots)
            }
        }
    )

    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        if (isInitialLaunch && selectedImageUris.isEmpty()) {
            galleryLauncher.launch("image/*")
            isInitialLaunch = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // White bar background for the bottom button area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(82.dp)
                .align(Alignment.BottomCenter)
                .background(Color.White)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Top Bar
            TopAppBar(
                title = { Text("New Post") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )

            // Tab Row
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.fillMaxWidth()
            ) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = { selectedTabIndex = 0 },
                    text = { Text("Gallery") },
                    icon = { Icon(Icons.Default.PhotoLibrary, contentDescription = null) }
                )
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1 },
                    text = { Text("Camera") },
                    icon = { Icon(Icons.Default.Camera, contentDescription = null) }
                )
            }

            // Content
            when (selectedTabIndex) {
                0 -> {
                    // Gallery Content
                    if (selectedImageUris.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .background(Color.LightGray)
                                .clickable { galleryLauncher.launch("image/*") },
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    Icons.Default.AddAPhoto,
                                    contentDescription = "Add Photos",
                                    modifier = Modifier.size(48.dp)
                                )
                                Text("Add Photos")
                            }
                        }
                    } else {
                        HorizontalPager(
                            count = selectedImageUris.size,
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                        ) { page ->
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                AsyncImage(
                                    model = selectedImageUris[page],
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Fit
                                )
                                IconButton(
                                    onClick = {
                                        selectedImageUris = selectedImageUris.toMutableList().also { it.removeAt(page) }
                                    },
                                    modifier = Modifier
                                        .align(Alignment.TopStart)
                                        .padding(8.dp)
                                ) {
                                    Icon(Icons.Default.Close, contentDescription = "Remove Image", tint = Color.White)
                                }
                            }
                        }
                        HorizontalPagerIndicator(
                            pagerState = pagerState,
                            pageCount = selectedImageUris.size,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(8.dp)
                        )

                        if (selectedImageUris.size < 5) {
                            Button(
                                onClick = { galleryLauncher.launch("image/*") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Add More Images")
                                Spacer(Modifier.width(4.dp))
                                Text("Add More")
                            }
                        }
                    }
                }
                1 -> {
                    // Camera Content
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Camera functionality coming soon")
                    }
                }
            }

            // Description
            OutlinedTextField(
                value = descriptionText,
                onValueChange = { descriptionText = it },
                label = { Text("Describe your post...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(120.dp)
            )

            // Price
            OutlinedTextField(
                value = priceText,
                onValueChange = { priceText = it },
                label = { Text("Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            // Condition
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    readOnly = true,
                    value = conditionText,
                    onValueChange = {},
                    label = { Text("Condition") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    conditions.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            onClick = {
                                conditionText = item
                                expanded = false
                            }
                        )
                    }
                }
            }

            // Location
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("Profile") }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Kathmandu Nepal", fontSize = 16.sp)
                }
                Icon(Icons.Default.ChevronRight, contentDescription = null)
            }

            // Spacer for bottom padding
            Spacer(modifier = Modifier.height(82.dp))
        }

        // Bottom Share Button
        Button(
            onClick = { /* TODO: Handle Share */ },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp),
            enabled = selectedImageUris.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Share", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAddScreen() {
    val previewNavController = rememberNavController()
    AddScreen(navController = previewNavController)
}


