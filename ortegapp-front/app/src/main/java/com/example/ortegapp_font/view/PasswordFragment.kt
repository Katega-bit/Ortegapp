package com.example.ortegapp_font.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ortegapp_font.R
import com.example.ortegapp_font.core.TokenManager
import com.example.ortegapp_font.databinding.FragmentPasswordBinding
import com.example.ortegapp_font.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PasswordFragment : Fragment() {

    private lateinit var binding : FragmentPasswordBinding
    private lateinit var repository: LoginRepository
    private lateinit var tokenManager : TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPasswordBinding.inflate(layoutInflater)
        repository = LoginRepository()
        tokenManager = TokenManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changePassword()
        backButton()

    }

     fun changePassword(){
         binding.btnChange.setOnClickListener {
             CoroutineScope(Dispatchers.IO).launch {

               val response =  repository.changePassword(
                     binding.etNewpassword.text.toString(),
                     binding.etRepeatpassword.text.toString(),
                     binding.etOldpassword.text.toString(),
                     tokenManager.getToken()!!
                 )

                 if (response!=null){

                     val fragmentTransaction = parentFragmentManager.beginTransaction()
                     fragmentTransaction.replace(R.id.container, ProfileFragment())
                     fragmentTransaction.commit()

                 }
             }

         }
    }

    fun backButton(){
        binding.backButton.setOnClickListener{
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, ProfileFragment())
            fragmentTransaction.commit()

        }
    }
}