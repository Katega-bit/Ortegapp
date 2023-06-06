package com.example.ortegapp_font.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ortegapp_font.R
import com.example.ortegapp_font.adapter.AdapterFavorite
import com.example.ortegapp_font.adapter.AdapterProducto
import com.example.ortegapp_font.core.TokenManager
import com.example.ortegapp_font.databinding.FragmentFavoriteBinding
import com.example.ortegapp_font.model.Producto
import com.example.ortegapp_font.viewmodel.FavoriteViewModel
import com.example.ortegapp_font.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavoriteFragment : Fragment() {

    private lateinit var binding : FragmentFavoriteBinding
    private lateinit var adapter : AdapterFavorite
    private val viewModel : FavoriteViewModel by viewModels()
    private val viewModelHome : HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    fun init(){
        adapter = AdapterFavorite(emptyList(),{likeProduct(it)},{navigateToDetail(it)})
        fetchLikes()
        binding.likeList.setHasFixedSize(true)
        binding.likeList.layoutManager = LinearLayoutManager(requireContext())
        binding.likeList.adapter = adapter


    }

    fun fetchLikes(){
        viewModel.initToken(requireContext())
        viewModelHome.initToken(requireContext())
        viewModel.fetchMeLikes()
        viewModel.productoLikeLiveData.observe(viewLifecycleOwner){response ->
            if (response == null){
                Toast.makeText(requireContext(), "Network call fail", Toast.LENGTH_LONG)
                return@observe
            }
            adapter.updateList(response.content)


        }
    }

    fun likeProduct(producto : Producto){
        CoroutineScope(Dispatchers.IO).launch {
            viewModelHome.likeProduct(producto.id!!)

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