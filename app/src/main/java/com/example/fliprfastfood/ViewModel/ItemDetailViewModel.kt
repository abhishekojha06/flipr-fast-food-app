package com.example.fliprfastfood.ViewModel


import android.content.ClipData.Item
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fliprfastfood.repository.ItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items

    fun fetchItems() {
        viewModelScope.launch {
            try {
                repository.getItems().also {
                    _items.value
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addItem(item: Item) {
        viewModelScope.launch {
            try {
                repository.addItem(item)
                fetchItems()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateItem(id: Int, item: Item) {
        viewModelScope.launch {
            try {
                repository.updateItem(id, item)
                fetchItems()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteItem(id: Int) {
        viewModelScope.launch {
            try {
                repository.deleteItem(id)
                fetchItems()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
