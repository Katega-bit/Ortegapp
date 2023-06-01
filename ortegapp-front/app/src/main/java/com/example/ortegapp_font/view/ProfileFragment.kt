package com.example.ortegapp_font.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.ortegapp_font.R
import com.example.ortegapp_font.core.TokenManager
import com.example.ortegapp_font.databinding.FragmentProfileBinding
import com.example.ortegapp_font.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private lateinit var tokenManager: TokenManager
    private lateinit var repository : LoginRepository


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        tokenManager = TokenManager(requireContext())
        repository = LoginRepository()
        return binding.root



    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logout()
        CoroutineScope(Dispatchers.IO).launch{
            withContext(Dispatchers.Main){
                init()
            }

        }
    }

    suspend fun init(){
        val response = repository.userData(tokenManager.getToken()!!)

        binding.fullName.text = response?.fullName
        binding.username.text =  response?.username
        Glide.with(requireContext())
            .load(response?.avatar)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.avatar)

    }

     fun logout(){

        binding.optionLogout.setOnClickListener {
            tokenManager.deleteToke()
           val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }


}