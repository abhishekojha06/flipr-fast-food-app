package com.example.fliprfastfood.Pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.fliprfastfood.R

import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun Home() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(9.dp))
        // Address and promotional banner
        TopBarSection()
        Spacer(modifier = Modifier.height(16.dp))

        // Carousel (Image Slider)
        ImageSlider(
            navController = rememberNavController(),
            imageUrls = listOf(
                "https://images.unsplash.com/photo-1513104890138-7c749659a591",
                "https://images.unsplash.com/photo-1555939594-58d7cb561ad1",
                "https://images.unsplash.com/photo-1561047029-3000e9341f9f",
                "https://images.unsplash.com/photo-1586190848861-99aa4a171e90",
                "https://images.unsplash.com/photo-1605475127673-1a894e2e9d1f",
                "https://images.unsplash.com/photo-1627308595229-7830a5c91f9f",
                "https://images.unsplash.com/photo-1606744827337-2a151d90b7f0",
                "https://images.unsplash.com/photo-1601050690173-4e24ab6d965f",
                "https://images.unsplash.com/photo-1604152135912-04a1e2c7a066",
                "https://images.unsplash.com/photo-1598511720014-ec4ff0ec58c8"
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Fastest Delivery Section
        ItemSection(
            navController = rememberNavController(),
            title = "Fastest Delivery",
            items = listOf("food1", "food2", "food3")
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Popular Items Section
        ItemSection(
            navController = rememberNavController(),

            title = "Popular Items",
            items = listOf("food4", "food5", "food6")

        )

        Spacer(modifier = Modifier.height(16.dp))

        //bottomnavigation
        BottomNavigationBar()
    }
}

// Top bar
@Composable
fun TopBarSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Home Logo
        Icon(
            painter = painterResource(id = R.drawable.home), // Replace with actual home logo resource
            contentDescription = "Home Logo",
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Address Text
        Text(
            text = "Home, Jl. Soekarno Hatta 15A",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Dropdown Button
        TextButton(onClick = { /* Handle dropdown or change address */ }) {
            Text(
                text = "Order Now",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }
    }
}

// Carousel Section (Image Slider)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(navController: NavController, imageUrls: List<String>) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { imageUrls.size })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrls),
            contentDescription = "Image",
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navController.navigate("ItemDetailPage")
                },
            contentScale = ContentScale.Crop
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(imageUrls.size) { index ->
            val color = if (pagerState.currentPage == index) Color.Black else Color.Gray
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(8.dp)
                    .background(color, shape = CircleShape)
            )
        }
    }
}


@Composable
fun ItemSection(navController: NavController, title: String, items: List<String>) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
Row(
modifier = Modifier.fillMaxWidth(),
horizontalArrangement = Arrangement.SpaceBetween,
verticalAlignment = Alignment.CenterVertically
) {
    Text(text = title, style = MaterialTheme.typography.bodyMedium)
    TextButton(onClick = { /* Handle See All */ }) {
        Text("See all")
    }
}

LazyRow(
modifier = Modifier.fillMaxWidth(),
horizontalArrangement = Arrangement.spacedBy(8.dp)
) {
    items(items) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.size(150.dp, 100.dp)
                .clickable {
                    navController.navigate("ItemDetailPage")
                }
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.foodimagess), // Placeholder image
                    contentDescription = "Abhishek",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
}
}


@Composable
fun BottomNavigationBar() {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = {  },
            icon = {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = "Admin Page"
                )
            },
            label = { Text("Admin") }
        )

        NavigationBarItem(
            selected = false,
            onClick = {          },
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile Page"
                )
            },
            label = { Text("Profile") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Sign Out"
                )
            },
            label = { Text("Sign Out") }
        )
    }
}
