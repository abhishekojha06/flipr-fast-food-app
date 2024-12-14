package com.example.fliprfastfood.repository

import android.content.ClipData
import com.example.fliprfastfood.api.ApiService

class ItemRepository(private val apiService: ApiService) {
    suspend fun getItems() = apiService.getItems()
    suspend fun addItem(item: ClipData.Item) = apiService.addItem(item)
    suspend fun updateItem(id: Int, item: ClipData.Item) = apiService.updateItem(id, item)
    suspend fun deleteItem(id: Int) = apiService.deleteItem(id)
}
