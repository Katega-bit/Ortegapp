package com.example.ortegapp_font.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ortegapp_font.R
import com.example.ortegapp_font.adapter.AdapterProducto
import com.example.ortegapp_font.databinding.FragmentHomeBinding
import com.example.ortegapp_font.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private val viewModel : HomeViewModel by viewModels()
    private lateinit var binding : FragmentHomeBinding
    private lateinit var adapter : AdapterProducto

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.tokenManagerInit(requireContext())
        init()
    }


    fun init(){
        adapter = AdapterProducto()
        binding.productoList.setHasFixedSize(true)
        binding.productoList.layoutManager = LinearLayoutManager(requireContext())
        binding.productoList.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productoList.collect{
                adapter.submitData(it)
            }

        }
    }


}