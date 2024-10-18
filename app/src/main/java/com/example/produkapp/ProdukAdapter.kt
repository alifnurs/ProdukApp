package com.example.produkapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProdukAdapter(
    private var produkList: List<Produk>,
    private val onDeleteClick: (Produk) -> Unit,
    private val onUpdateClick: (Produk) -> Unit
) : RecyclerView.Adapter<ProdukAdapter.ProdukViewHolder>() {

    inner class ProdukViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        private val tvKategori: TextView = itemView.findViewById(R.id.tvKategori)
        private val tvHarga: TextView = itemView.findViewById(R.id.tvHarga)
        private val btnUpdate: Button = itemView.findViewById(R.id.btnUpdate)
        private val btnHapus: Button = itemView.findViewById(R.id.btnHapus)

        fun bind(produk: Produk) {
            tvNama.text = produk.nama
            tvKategori.text = produk.kategori
            tvHarga.text = produk.harga.toString()

            btnUpdate.setOnClickListener {
                onUpdateClick(produk)
            }

            btnHapus.setOnClickListener {
                onDeleteClick(produk)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdukViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produk, parent, false)
        return ProdukViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdukViewHolder, position: Int) {
        holder.bind(produkList[position])
    }

    override fun getItemCount() = produkList.size

    fun updateProdukList(newProdukList: List<Produk>) {
        produkList = newProdukList
        notifyDataSetChanged()
    }
}
