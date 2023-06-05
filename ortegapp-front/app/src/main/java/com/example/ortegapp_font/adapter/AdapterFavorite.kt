package com.example.ortegapp_font.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ortegapp_font.R
import com.example.ortegapp_font.databinding.ItemListFavoriteBinding
import com.example.ortegapp_font.databinding.ItemListProductoBinding
import com.example.ortegapp_font.model.Producto
import com.squareup.picasso.Picasso

class AdapterFavorite(var productoList: List<Producto> = emptyList(),
                      val likeListener: (Producto) -> Unit,
                      val onItemSelected : (Int) -> Unit)
    : RecyclerView.Adapter<AdapterFavorite.FavoriteViewHolder>() {

    fun updateList(productoList : List<Producto>){
        if (productoList != null){
            this.productoList = productoList
            notifyDataSetChanged()

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteViewHolder {
        val binding = ItemListFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productoList.size
    }

    override fun onBindViewHolder(holder: AdapterFavorite.FavoriteViewHolder, position: Int) {
        holder.bind(productoList[position], likeListener, onItemSelected)
    }

    class FavoriteViewHolder(private val binding : ItemListFavoriteBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(productoItem: Producto, likeListener : (Producto) -> Unit, onItemSelected: (Int) -> Unit){
            Picasso.get().load(productoItem.foto).into(binding.productoImage)
            binding.productoName.text = productoItem.nombre

            binding.likeButton.setOnClickListener {
                likeListener(productoItem)

            }
            binding.productoCard.setOnClickListener{

                onItemSelected(productoItem.id!!)
            }
        }
    }
}