package com.example.produkapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produk")
data class Produk(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var nama: String,
    var kategori: String,
    var harga: Int
)
