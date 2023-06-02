package com.example.ortegapp_font.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ortegapp_font.databinding.ItemListProductoBinding
import com.example.ortegapp_font.model.Producto

class AdapterProducto() : PagingDataAdapter<Producto, AdapterProducto.ProductoViewHolder>(ProductoDiffCallBack()) {



    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        return ProductoViewHolder(
            ItemListProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    class ProductoDiffCallBack : DiffUtil.ItemCallback<Producto>(){
        override fun areItemsTheSame(oldItem: Producto, newItem: Producto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Producto, newItem: Producto): Boolean {
            return oldItem == newItem
        }

    }

    class ProductoViewHolder(private val binding : ItemListProductoBinding) :  RecyclerView.ViewHolder(binding.root){
        fun bind(item : Producto){
            binding.productoName.text = item.nombre


        }

    }
}