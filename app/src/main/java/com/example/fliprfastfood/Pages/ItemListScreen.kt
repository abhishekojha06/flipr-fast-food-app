package com.example.fliprfastfood.Pages

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.fliprfastfood.ViewModel.ItemViewModel
import com.example.fliprfastfood.data.ItemDetail
import androidx.compose.foundation.lazy.LazyColumn as LazyColumn1

@Composable
fun ItemListScreen(viewModel: ItemViewModel) {
    val items by viewModel.items.collectAsState()

    LazyColumn1 {

    }

    FloatingActionButton(onClick = { /* Navigate to AddEditItemScreen */ }) {
        Text("+")
    }
}
