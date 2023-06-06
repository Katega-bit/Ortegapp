package com.example.ortegapp_font.adapter

import android.app.Fragment
import android.content.Context
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ortegapp_font.R
import com.example.ortegapp_font.databinding.FragmentDetailProductoBinding
import com.example.ortegapp_font.databinding.ItemListProductoBinding
import com.example.ortegapp_font.model.Producto
import com.example.ortegapp_font.model.ProductoResponse
import com.example.ortegapp_font.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdapterProducto(
    var productoList: List<Producto> = emptyList(),
    val likeListener: (Producto) -> Unit,
    val onItemSelected : (Int) -> Unit,
    val vm : HomeViewModel
) : RecyclerView.Adapter<AdapterProducto.ProductoViewHodler>() {


    fun updateList(productoList : List<Producto>){
        if (productoList != null){
            this.productoList = productoList
            notifyDataSetChanged()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHodler {
       val binding = ItemListProductoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductoViewHodler(binding)
    }

    override fun getItemCount(): Int {
        return productoList.size
    }

    override fun onBindViewHolder(holder: ProductoViewHodler, position: Int) {
        holder.bind(productoList[position], likeListener, onItemSelected, vm)
    }

    class ProductoViewHodler(private val binding : ItemListProductoBinding): RecyclerView.ViewHolder(binding.root){


        fun bind(productoItem: Producto, likeListener : (Producto) -> Unit, onItemSelected: (Int) -> Unit, vm : HomeViewModel){
            Picasso.get().load(productoItem.foto).into(binding.productoImage)
            binding.productoName.text = productoItem.nombre
          var checked = false
            binding.likeButton.setOnClickListener {
                likeListener(productoItem)
                if (!checked){
                    binding.likeButton.setBackgroundResource(R.drawable.like)
                    checked = true

                }
                else{
                    binding.likeButton.setBackgroundResource(R.drawable.favorite_border)
                    checked = false

                }
            }
            binding.productoCard.setOnClickListener{

                onItemSelected(productoItem.id!!)
            }

            CoroutineScope(Dispatchers.Main).launch {
                val myLikes = vm.fetchMeLikes()
                myLikes.forEach{like ->
                    if (productoItem.nombre == like.nombre){
                        binding.likeButton.setBackgroundResource(R.drawable.like)
                        checked = true
                        }
                }
            }
        }
    }
}