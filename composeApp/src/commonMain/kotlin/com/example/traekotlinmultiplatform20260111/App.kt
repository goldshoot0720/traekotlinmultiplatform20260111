package com.example.traekotlinmultiplatform20260111

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.traekotlinmultiplatform20260111.ui.AppBackground
import com.example.traekotlinmultiplatform20260111.ui.AppPrimary
import com.example.traekotlinmultiplatform20260111.ui.AppSecondary
import com.example.traekotlinmultiplatform20260111.ui.TextPrimary
import com.example.traekotlinmultiplatform20260111.ui.TextSecondary
import com.example.traekotlinmultiplatform20260111.ui.pages.BankWeeklyScreen
import com.example.traekotlinmultiplatform20260111.ui.pages.FoodScreen
import com.example.traekotlinmultiplatform20260111.ui.pages.HomeScreen
import com.example.traekotlinmultiplatform20260111.ui.pages.ImageGalleryScreen
import com.example.traekotlinmultiplatform20260111.ui.pages.MusicLyricsScreen
import com.example.traekotlinmultiplatform20260111.ui.pages.SettingsScreen
import com.example.traekotlinmultiplatform20260111.ui.pages.SubscriptionScreen
import com.example.traekotlinmultiplatform20260111.ui.pages.VideoLibraryScreen
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class Page(val title: String) {
    Home("首頁"),
    ImageGallery("圖片庫"),
    VideoLibrary("影片庫"),
    MusicLyrics("鋒兄音樂歌詞"),
    BankWeekly("銀行週記"),
    Subscription("訂閱管理"),
    Food("食品管理"),
    Settings("系統設定")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var currentPage by remember { mutableStateOf(Page.Home) }

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = AppPrimary,
                    drawerContentColor = TextPrimary
                ) {
                    Spacer(Modifier.height(24.dp))
                    Text(
                        "鋒兄AI資訊系統",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    HorizontalDivider(color = AppSecondary)
                    Spacer(Modifier.height(12.dp))
                    
                    Page.values().forEach { page ->
                        NavigationDrawerItem(
                            label = { Text(page.title) },
                            selected = currentPage == page,
                            onClick = {
                                currentPage = page
                                scope.launch { drawerState.close() }
                            },
                            icon = {
                                when(page) {
                                    Page.Home -> Icon(Icons.Default.Home, null)
                                    Page.ImageGallery -> Icon(Icons.Default.Face, null)
                                    Page.VideoLibrary -> Icon(Icons.Default.PlayArrow, null)
                                    Page.MusicLyrics -> Icon(Icons.Default.Favorite, null)
                                    Page.BankWeekly -> Icon(Icons.Default.DateRange, null)
                                    Page.Subscription -> Icon(Icons.Default.List, null)
                                    Page.Food -> Icon(Icons.Default.ShoppingCart, null)
                                    Page.Settings -> Icon(Icons.Default.Settings, null)
                                }
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = AppSecondary,
                                selectedTextColor = TextPrimary,
                                selectedIconColor = TextPrimary,
                                unselectedTextColor = TextSecondary,
                                unselectedIconColor = TextSecondary
                            ),
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                }
            }
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text(currentPage.title) },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = AppPrimary,
                            titleContentColor = TextPrimary,
                            navigationIconContentColor = TextPrimary,
                            actionIconContentColor = TextPrimary
                        )
                    )
                },
                containerColor = AppBackground
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(AppBackground)
                ) {
                    when (currentPage) {
                        Page.Home -> HomeScreen()
                        Page.ImageGallery -> ImageGalleryScreen()
                        Page.VideoLibrary -> VideoLibraryScreen()
                        Page.MusicLyrics -> MusicLyricsScreen()
                        Page.BankWeekly -> BankWeeklyScreen()
                        Page.Subscription -> SubscriptionScreen()
                        Page.Food -> FoodScreen()
                        Page.Settings -> SettingsScreen()
                    }
                }
            }
        }
    }
}
