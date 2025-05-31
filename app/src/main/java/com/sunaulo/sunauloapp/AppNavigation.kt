package com.sunaulo.sunauloapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.sunaulo.sunauloapp.screens.HomeScreen
import com.sunaulo.sunauloapp.screens.SearchScreen
import com.sunaulo.sunauloapp.screens.AddScreen
import com.sunaulo.sunauloapp.screens.ProfileScreen
import com.sunaulo.sunauloapp.screens.PostDetailsScreen
import com.sunaulo.sunauloapp.screens.ChatListScreen
import com.sunaulo.sunauloapp.screens.ChatDetailScreen
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object Add : Screen("add")
    object Message : Screen("message")
    object Profile : Screen("profile")
    object PostDetails : Screen("postdetails/{postId}") {
        const val POST_ID_KEY = "postId"
    }
    object ChatDetail : Screen("chatdetail/{chatId}") {
        const val CHAT_ID_KEY = "chatId"
    }
}

data class BottomNavItem(val name: String, val icon: ImageVector, val route: String)

val bottomNavItems = listOf(
    BottomNavItem("Home", Icons.Default.Home, Screen.Home.route),
    BottomNavItem("Search", Icons.Default.Search, Screen.Search.route),
    BottomNavItem("Add", Icons.Default.Add, Screen.Add.route),
    BottomNavItem("Message", Icons.Default.Send, Screen.Message.route),
    BottomNavItem("Profile", Icons.Default.Person, Screen.Profile.route)
)

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screen.Home.route, modifier = modifier) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Search.route) {
            SearchScreen()
        }
        composable(Screen.Add.route) {
            AddScreen(navController = navController)
        }
        composable(Screen.Message.route) {
            ChatListScreen(navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
        composable(Screen.PostDetails.route) { backStackEntry ->
            val postId = backStackEntry.arguments?.getString(Screen.PostDetails.POST_ID_KEY)
            PostDetailsScreen(postId = postId)
        }
        composable(
            route = Screen.ChatDetail.route,
            arguments = listOf(navArgument(Screen.ChatDetail.CHAT_ID_KEY) { type = androidx.navigation.NavType.IntType })
        ) { backStackEntry ->
            val chatId = backStackEntry.arguments?.getInt(Screen.ChatDetail.CHAT_ID_KEY)
            ChatDetailScreen(chatId = chatId)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    // Don't show navigation bar if we're in chat detail screen or add screen
    if (currentRoute?.startsWith("chatdetail") == true || currentRoute == Screen.Add.route) {
        return
    }
    
    NavigationBar {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.name) },
                label = { Text(item.name) },
                alwaysShowLabel = true
            )
        }
    }
} 