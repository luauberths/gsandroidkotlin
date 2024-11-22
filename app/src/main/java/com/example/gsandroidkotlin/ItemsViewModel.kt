package com.example.gsandroidkotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsViewModel(application: Application) : AndroidViewModel(application) {
    private val itemDao: ItemDao
    val itemsLiveData: LiveData<List<ItemModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            ItemDatabase::class.java,
            "items_database"
        ).fallbackToDestructiveMigration() .build()

        itemDao = database.itemDao()
        itemsLiveData = itemDao.getAll()
    }

    fun addItem(name: String, tip: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newItem = ItemModel(name = name, tip = tip)
            itemDao.insert(newItem)
        }
    }


    fun removeItem(item: ItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.delete(item)
        }
    }
}