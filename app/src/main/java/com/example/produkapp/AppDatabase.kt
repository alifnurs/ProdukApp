package com.example.produkapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Produk::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produkDao(): ProdukDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "produk_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
