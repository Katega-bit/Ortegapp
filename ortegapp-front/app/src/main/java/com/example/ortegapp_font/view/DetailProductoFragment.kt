package com.example.ortegapp_font.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ortegapp_font.R
import com.example.ortegapp_font.adapter.AdapterComment
import com.example.ortegapp_font.core.TokenManager
import com.example.ortegapp_font.databinding.FragmentDetailProductoBinding
import com.example.ortegapp_font.repository.LoginRepository
import com.example.ortegapp_font.repository.ProductoRepository
import com.example.ortegapp_font.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailProductoFragment : Fragment() {

    private var idProducto : Int = 0
    private val viewModel : HomeViewModel by viewModels()
    private lateinit var adapterComment : AdapterComment
    private lateinit var binding : FragmentDetailProductoBinding
    private lateinit var repository: ProductoRepository
    private lateinit var tokenManager: TokenManager
    private lateinit var loginRepository: LoginRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val bundle = arguments
        val data = bundle?.getInt("productoId")
        idProducto = data!!
        repository = ProductoRepository()
        loginRepository = LoginRepository()
        tokenManager = TokenManager(requireContext())
        binding = FragmentDetailProductoBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        backButton()
        commentImage()
        postComment()

    }

    fun init(){
        adapterComment = AdapterComment()
        binding.commentList.setHasFixedSize(true)
        binding.commentList.layoutManager = LinearLayoutManager(requireContext())
        binding.commentList.adapter = adapterComment
        viewModel.initToken(requireContext())
        viewModel.productoByid(idProducto)
        viewModel.productoIdLiveData.observe(viewLifecycleOwner){
            binding.productDetailName.text = it?.nombre
            binding.productoDetailPrice.text = it?.precio.toString()+" €"
            Picasso.get().load(it?.foto).into(binding.productoDetailImage)


            if (it != null){
                adapterComment.updateList(it.comentarios)
            }

        }

    }

    fun postComment(){

        binding.commentButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                repository.postComment(idProducto, tokenManager.getToken()!!, binding.commentBox.text.toString())
            }
        }
    }

    fun backButton(){
        binding.backButton.setOnClickListener{
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, HomeFragment())
            fragmentTransaction.commit()

        }
    }

    fun commentImage(){
        CoroutineScope(Dispatchers.IO).launch{
            withContext(Dispatchers.Main) {
             val image = loginRepository.userData(tokenManager.getToken()!!)?.avatar
                Picasso.get().load(image).into(binding.posCommentAvatar)
            }
        }

    }


}