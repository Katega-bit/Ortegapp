package com.example.ortegapp_font.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ortegapp_font.R
import com.example.ortegapp_font.adapter.AdapterProducto
import com.example.ortegapp_font.databinding.FragmentHomeBinding
import com.example.ortegapp_font.databinding.ItemListProductoBinding
import com.example.ortegapp_font.model.Producto
import com.example.ortegapp_font.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var bindingItem : ItemListProductoBinding
    private lateinit var adapter : AdapterProducto
    private val viewModel : HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        bindingItem = ItemListProductoBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    fun init(){
        fetchProduct()
        adapter = AdapterProducto(emptyList(),{likeProduct(it)},{navigateToDetail(it)})
        binding.productoList.setHasFixedSize(true)
        binding.productoList.layoutManager = LinearLayoutManager(requireContext())
        binding.productoList.adapter = adapter

    }


    fun fetchProduct(){

        viewModel.initToken(requireContext())
        viewModel.fetchProductos()
        viewModel.productoLiveData.observe(viewLifecycleOwner){
                response ->
            if (response == null){
                Toast.makeText(requireContext(), "Network call fail", Toast.LENGTH_LONG)
                return@observe
            }
            adapter.updateList(response.content)

        }


    }

    fun likeProduct(producto : Producto){
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.likeProduct(producto.id!!)
        }
    }
    private fun navigateToDetail(id:Int){

        val fragmentoDetalle = DetailProductoFragment()
        val bundle = Bundle()
        fragmentoDetalle.arguments = bundle

        bundle.putInt("productoId", id)

        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragmentoDetalle)
        fragmentTransaction.addToBackStack(null) // Opcional: agregar a la pila de retroceso
        fragmentTransaction.commit()
    }


}