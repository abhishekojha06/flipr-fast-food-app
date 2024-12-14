package com.example.fliprfastfood.Pages

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ItemDetailPage(
    itemName: String,
    description: String,
    originalPrice: Int,
    discountedPrice: Int,
    packageCost: Int,
    onBackClick: () -> Unit,
    onAddToOrderClick: Int,
    modifier: Modifier,
    navController: NavController
) {
    var quantity by remember { mutableStateOf(1) }
    val totalPrice = discountedPrice + (quantity * packageCost)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text(text = itemName, style = MaterialTheme.typography.displaySmall)
            IconButton(onClick = { /* Close logic */ }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
            }
        }

        // Image


        Spacer(modifier = Modifier.height(16.dp))

        // Description
        Text(text = description, style = MaterialTheme.typography.displaySmall)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "€${discountedPrice}", style = MaterialTheme.typography.displaySmall, color = Color.Red)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "€${originalPrice}",
                style = MaterialTheme.typography.displaySmall,
                color = Color.Gray,
                textDecoration = TextDecoration.LineThrough
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add-ons
        Text(text = "Add more", style = MaterialTheme.typography.displaySmall)


        Spacer(modifier = Modifier.height(16.dp))

        // Package Cost
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Package box cost", style = MaterialTheme.typography.displaySmall)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "+€${packageCost}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quantity Selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { if (quantity > 1) quantity-- }) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Decrease Quantity")
            }
            Text(text = quantity.toString(), style = MaterialTheme.typography.displaySmall)
            IconButton(onClick = { quantity++ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Increase Quantity")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add to Order Button
        Button(
            onClick = {  },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add to order (€${"%.2f".format(totalPrice)})")
        }
    }
}
