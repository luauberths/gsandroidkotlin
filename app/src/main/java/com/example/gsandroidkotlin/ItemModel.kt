package com.example.gsandroidkotlin

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val tip: String
)