package com.example.produkapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProdukViewModel(application: Application) : AndroidViewModel(application) {
    private val produkDao = AppDatabase.getDatabase(application).produkDao()
    val produkList = MutableLiveData<List<Produk>>()

    fun insertProduk(produk: Produk) = viewModelScope.launch {
        produkDao.insert(produk)
        refreshList()
    }

    fun updateProduk(produk: Produk) = viewModelScope.launch {
        produkDao.update(produk)
        refreshList()
    }

    fun deleteProduk(produk: Produk) = viewModelScope.launch {
        produkDao.delete(produk)
        refreshList()
    }

    fun refreshList() = viewModelScope.launch {
        produkList.postValue(produkDao.getAll())
    }

    suspend fun getProdukById(id: Int): Produk? = produkDao.getById(id)
}
