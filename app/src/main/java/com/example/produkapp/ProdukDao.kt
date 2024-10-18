package com.example.produkapp

import androidx.room.*

@Dao
interface ProdukDao {
    @Insert
    suspend fun insert(produk: Produk)

    @Update
    suspend fun update(produk: Produk)

    @Delete
    suspend fun delete(produk: Produk)

    @Query("SELECT * FROM produk ORDER BY id DESC")
    suspend fun getAll(): List<Produk>

    @Query("SELECT * FROM produk WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Produk?
}
