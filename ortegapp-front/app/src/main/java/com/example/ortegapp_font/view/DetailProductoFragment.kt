package com.example.ortegapp_font.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.ortegapp_font.R
import com.example.ortegapp_font.databinding.FragmentDetailProductoBinding
import com.example.ortegapp_font.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso

class DetailProductoFragment : Fragment() {

    private var idProducto : Int = 0
    private val viewModel : HomeViewModel by viewModels()
    private lateinit var binding : FragmentDetailProductoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val bundle = arguments
        val data = bundle?.getInt("productoId")
        idProducto = data!!
        binding = FragmentDetailProductoBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        backButton()

    }

    fun init(){
        viewModel.initToken(requireContext())
        viewModel.productoByid(idProducto)
        viewModel.productoIdLiveData.observe(viewLifecycleOwner){
            binding.productDetailName.text = it?.nombre
            binding.productoDetailPrice.text = it?.precio.toString()+" €"
            Picasso.get().load(it?.foto).into(binding.productoDetailImage)

        }
    }

    fun backButton(){
        binding.backButton.setOnClickListener{
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, HomeFragment())
            fragmentTransaction.commit()

        }
    }
}