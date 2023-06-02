package com.example.ortegapp_font.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ortegapp_font.R
import com.example.ortegapp_font.adapter.AdapterProducto
import com.example.ortegapp_font.databinding.FragmentHomeBinding
import com.example.ortegapp_font.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var adapter : AdapterProducto
    private val viewModel : HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        //viewModel.tokenManagerInit(requireContext())
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  init()
        arrancaLamoto()
        gasolinaPaLaMoto()

    }


    fun init(){
        binding.productoList.layoutManager = LinearLayoutManager(requireContext())
        binding.productoList.setHasFixedSize(true)
        binding.productoList.adapter = adapter

       // viewModel.productoList.observe(viewLifecycleOwner, Observer {
        //    Log.e("lista", viewModel.productoList.toString())
        //    adapter.submitData(lifecycle, it)
      //  })

    }
    fun arrancaLamoto(){
        adapter = AdapterProducto()
        binding.productoList.layoutManager = LinearLayoutManager(requireContext())
        binding.productoList.setHasFixedSize(true)
        binding.productoList.adapter = adapter
    }

    fun gasolinaPaLaMoto(){
        lifecycleScope.launch {
            viewModel.tokenManagerInit(requireContext())
            viewModel.lista.collect{
                adapter.submitData(it)
            }
        }
    }


}