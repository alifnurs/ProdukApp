package com.example.produkapp

import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val viewModel: ProdukViewModel by viewModels()
    private lateinit var etNama: EditText
    private lateinit var etKategori: EditText
    private lateinit var etHarga: EditText
    private lateinit var btnSimpan: Button
    private lateinit var rvProduk: RecyclerView
    private lateinit var adapter: ProdukAdapter
    private var currentProduk: Produk? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNama = findViewById(R.id.etNama)
        etKategori = findViewById(R.id.etKategori)
        etHarga = findViewById(R.id.etHarga)
        btnSimpan = findViewById(R.id.btnSimpan)
        rvProduk = findViewById(R.id.rvProduk)

        adapter = ProdukAdapter(emptyList(), { produk ->
            viewModel.deleteProduk(produk)
            Toast.makeText(this, "Produk dihapus", Toast.LENGTH_SHORT).show()
        }, { produk ->
            showUpdateDialog(produk)
        })

        rvProduk.adapter = adapter
        rvProduk.layoutManager = LinearLayoutManager(this)

        btnSimpan.setOnClickListener {
            if (validateInput()) {
                val nama = etNama.text.toString()
                val kategori = etKategori.text.toString()
                val harga = etHarga.text.toString().toInt()

                if (currentProduk != null) {
                    currentProduk?.let {
                        it.nama = nama
                        it.kategori = kategori
                        it.harga = harga
                        viewModel.updateProduk(it)
                    }
                    currentProduk = null
                } else {
                    val produk = Produk(nama = nama, kategori = kategori, harga = harga)
                    viewModel.insertProduk(produk)
                }
                clearInput()
            }
        }

        viewModel.produkList.observe(this) { produks ->
            adapter.updateProdukList(produks)
        }

        viewModel.refreshList()
    }

    private fun showUpdateDialog(produk: Produk) {
        currentProduk = produk
        etNama.setText(produk.nama)
        etKategori.setText(produk.kategori)
        etHarga.setText(produk.harga.toString())
    }

    private fun validateInput(): Boolean {
        return when {
            etNama.text.isEmpty() -> {
                etNama.error = "Nama tidak boleh kosong"
                false
            }
            etKategori.text.isEmpty() -> {
                etKategori.error = "Kategori tidak boleh kosong"
                false
            }
            etHarga.text.isEmpty() -> {
                etHarga.error = "Harga tidak boleh kosong"
                false
            }
            else -> true
        }
    }

    private fun clearInput() {
        etNama.text.clear()
        etKategori.text.clear()
        etHarga.text.clear()
    }
}
