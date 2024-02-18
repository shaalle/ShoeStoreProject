package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import java.util.Objects


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_login, container, false)
        val binding : FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )


        binding.btnLogIn.setOnClickListener {
            if(binding.editTxtEmail.text.isEmpty() || binding.editTxtPass.text.isEmpty()){
                val snackbar = Snackbar.make(it, "Email/Password can't be empty", Snackbar.LENGTH_SHORT)
                snackbar.show()
            }else{
                val snackbar = Snackbar.make(it, "You Logged Successfully", Snackbar.LENGTH_SHORT)
                snackbar.show()
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        }


        binding.btnSignUp.setOnClickListener {
            if(binding.editTxtEmail.text.isEmpty() || binding.editTxtPass.text.isEmpty()){
                val snackbar = Snackbar.make(it, "Email/Password can't be empty", Snackbar.LENGTH_SHORT)
                snackbar.show()
            }else{
                val snackbar = Snackbar.make(it, "You SignedUp Successfully", Snackbar.LENGTH_SHORT)
                snackbar.show()
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        }
        return binding.root
    }

}